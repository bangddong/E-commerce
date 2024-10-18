package com.hanghae.ecommerce.common.response;

import com.hanghae.ecommerce.common.exception.BaseException;
import com.hanghae.ecommerce.common.interceptor.RequestIdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.coyote.BadRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.MDC;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
class ApiControllerAdvice {

    /**
     * 500
     * 시스템 예외 상황으로 집중 모니터링 필요
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public <T> CommonResponse<T> onException(Exception e) {
        String eventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
        log.error("eventId = {} ", eventId, e);
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
    }

    /**
     * 200
     * 시스템은 이슈 없고, 비즈니스 로직 처리에서 발생한 에러
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BaseException.class)
    public <T> CommonResponse<T> onBaseException(BaseException e) {
        String eventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
        log.error("[BaseException] eventId = {}, cause = {}, errorMsg = {}",
                eventId,
                NestedExceptionUtils.getMostSpecificCause(e),
                NestedExceptionUtils.getMostSpecificCause(e).getMessage()
        );
        return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
    }

    /**
     * 200
     * 클라이언트의 연결 종료와 같은 서버 장애가 아닌 스킵 가능한 에러
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {ClientAbortException.class})
    public <T> CommonResponse<T> skipException(Exception e) {
        String eventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
        log.warn("[SkipException] eventId = {}, cause = {}, errorMsg = {}",
                eventId,
                NestedExceptionUtils.getMostSpecificCause(e),
                NestedExceptionUtils.getMostSpecificCause(e).getMessage()
        );
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
    }

    /**
     * 400
     * 클라이언트의 잘못된 요청
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadRequestException.class})
    public <T> CommonResponse<T> badRequestException(Exception e) {
        return CommonResponse.fail(ErrorCode.COMMON_BAD_REQUEST);
    }

    /**
     * 404
     * 클라이언트의 잘못된 리소스 요청
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoResourceFoundException.class})
    public <T> CommonResponse<T> notFoundException(Exception e) {
        return CommonResponse.fail(ErrorCode.COMMON_NOT_FOUND);
    }

    /**
     * 409
     * 동시 요청으로 인한 에러
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    public <T> CommonResponse<T> conflictException(Exception e) {
        String eventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
        log.error("[Conflict Exception] eventId = {}, cause = {}, errorMsg = {}",
                eventId,
                NestedExceptionUtils.getMostSpecificCause(e),
                NestedExceptionUtils.getMostSpecificCause(e).getMessage()
        );
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
    }

}
