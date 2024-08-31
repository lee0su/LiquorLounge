package com.lee0su.LiquorLounge.core.controller.session;

import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/session")
@RestController
public class SessionController {

    @GetMapping("/username")
    public ResponseEntity<Map<String, Object>> getUserSession(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            response.put("username", user.getUsername());
        } else {
            response.put("username", "X");
        }

        return ResponseEntity.ok(response);
    }

}
