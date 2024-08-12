package com.lee0su.LiquorLounge.core.guest.controller;

import com.lee0su.LiquorLounge.core.guest.dto.login.LoginRequest;
import com.lee0su.LiquorLounge.core.guest.dto.login.LoginResponse;
import com.lee0su.LiquorLounge.core.guest.dto.UserDTO;
import com.lee0su.LiquorLounge.core.guest.entity.UserEntity;
import com.lee0su.LiquorLounge.core.guest.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<Void> registerUser(@ModelAttribute UserDTO userDTO, HttpServletResponse response) {
        try {
            UserEntity user = new UserEntity();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setBirthdate(userDTO.getBirthdate());

            userService.registerUser(user);

            response.setHeader("Location", "/guest/signIn");
            return ResponseEntity.status(HttpStatus.FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean isAvailable = userService.isUsernameAvailable(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isAvailable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UserEntity user = userService.signIn(loginRequest.getUsername(), loginRequest.getPassword(), session);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
        }
        return ResponseEntity.ok(new LoginResponse(user.getName()));
    }

}
