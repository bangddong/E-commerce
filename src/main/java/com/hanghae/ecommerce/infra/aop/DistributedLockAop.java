package com.hanghae.ecommerce.infra.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {
	private static final String REDISSON_LOCK_PREFIX = "LOCK:";

	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;

	@Around("@annotation(com.hanghae.ecommerce.infra.aop.DistributedLock)")
	public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

		String key = REDISSON_LOCK_PREFIX +
			CustomSpringELParser.getDynamicValue(
				signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key()
			);
		RLock rLock = redissonClient.getLock(key); // Lock을 획득한다.

		try {
			boolean available = rLock.tryLock( // 정해진 시간동안 Lock 획득을 위해 대기한다.
				distributedLock.waitTime(),
				distributedLock.leaseTime(),
				distributedLock.timeUnit()
			);
			if (!available) {
				return false;
			}

			return aopForTransaction.proceed(joinPoint); // 락을 획득한 후에 새 트랜잭션을 시작한다.
		} catch (InterruptedException e) {
			throw new InterruptedException();
		} finally {
			try {
				rLock.unlock(); // 종료시 락을 해제한다.
			} catch (IllegalMonitorStateException e) {
				log.info("Redisson Lock Already UnLock {} {}",
					"serviceName" + method.getName(),
					"key" + key
				);
			}
		}
	}

}
