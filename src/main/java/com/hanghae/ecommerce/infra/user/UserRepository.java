package com.hanghae.ecommerce.infra.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
