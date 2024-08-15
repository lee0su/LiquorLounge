package com.lee0su.LiquorLounge.core.service.user.test;

import com.lee0su.LiquorLounge.core.dto.user.UserDTO;
import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import com.lee0su.LiquorLounge.core.repository.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestUserData {

    // 테스트 계정 정보
    String id = "test12";
    String pw = "qwe";
    String name = "채채";
    String email = "example@gmail.com";
    LocalDate birthdate = LocalDate.of(2004, 5, 18);
    String rating = "일반";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @PostConstruct
    public void init() {

        UserDTO userDTO = new UserDTO();
        UserEntity user = new UserEntity();

        userDTO.setUsername(id);
        String encodedPassword = passwordEncoder.encode(pw);
        userDTO.setPassword(encodedPassword);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setBirthdate(birthdate);
        userDTO.setRating(rating);

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBirthdate(userDTO.getBirthdate());
        user.setRating(userDTO.getRating());

        userRepository.save(user);
    }
}