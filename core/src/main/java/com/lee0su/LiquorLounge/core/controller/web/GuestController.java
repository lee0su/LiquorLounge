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

    @GetMapping("/sign-in")
    public String signInPage() {
        return "guest/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "guest/sign-up";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "guest/forgot-password";
    }

    @GetMapping("/recommend/whiskey")
    public String recommendWhiskeyPage() {
        return "guest/recommend-whiskey";
    }

    @GetMapping("/recommend/cocktail")
    public String recommendCocktailPage() {
        return "guest/recommend-cocktail";
    }

}
