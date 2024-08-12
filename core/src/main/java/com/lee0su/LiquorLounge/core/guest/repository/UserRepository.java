package com.lee0su.LiquorLounge.core.guest.repository;

import com.lee0su.LiquorLounge.core.guest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
