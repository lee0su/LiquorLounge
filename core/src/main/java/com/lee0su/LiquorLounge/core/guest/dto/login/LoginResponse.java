package com.lee0su.LiquorLounge.core.guest.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String username;

    public LoginResponse(String username) {
        this.username = username;
    }
}
