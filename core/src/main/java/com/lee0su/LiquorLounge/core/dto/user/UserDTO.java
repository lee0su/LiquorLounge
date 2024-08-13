package com.lee0su.LiquorLounge.core.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDate birthdate;
    private String rating;
}
