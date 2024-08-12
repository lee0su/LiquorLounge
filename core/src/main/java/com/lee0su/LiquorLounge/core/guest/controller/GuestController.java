package com.lee0su.LiquorLounge.core.guest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/main")
    public String mainPage() {
        return "guest/main";
    }

    @GetMapping("/liquor")
    public String Page2() {
        return "guest/liquor";
    }

    @GetMapping("/community")
    public String Page3() {
        return "guest/community";
    }

    @GetMapping("/signIn")
    public String signInPage() {
        return "guest/signIn";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "guest/signUp";
    }

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "guest/forgotPassword";
    }

}
