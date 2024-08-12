package com.lee0su.LiquorLounge.core.guest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private LocalDate birthdate;
}
