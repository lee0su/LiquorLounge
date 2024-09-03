package com.lee0su.LiquorLounge.core.repository.user;

import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
    UserEntity findPasswordByUsernameAndNameAndEmail(String id, String name, String email);
}
