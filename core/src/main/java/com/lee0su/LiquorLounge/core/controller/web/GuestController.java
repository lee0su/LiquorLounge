package com.lee0su.LiquorLounge.core.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/main")
    public String guestMainPage() {
        return "guest/main";
    }

    @GetMapping("/liquor")
    public String guestLiquorPage() {
        return "guest/liquor";
    }

    @GetMapping("/community")
    public String guestCommunityPage() {
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
