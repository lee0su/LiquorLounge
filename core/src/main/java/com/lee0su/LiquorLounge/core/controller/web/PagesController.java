package com.lee0su.LiquorLounge.core.controller.web;

import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class PagesController {

    @GetMapping("/main")
    public String mainPage() {
        return "pages/main";
    }

    @GetMapping("/recommend-whiskey")
    public String recommendWhiskeyPage() {
        return "pages/recommend-whiskey";
    }

    @GetMapping("/recommend-cocktail")
    public String recommendCocktailPage() {
        return "pages/recommend-cocktail";
    }

    @GetMapping("/liquor")
    public String liquorPage() {
        return "pages/liquor";
    }

    @GetMapping("/community")
    public String communityPage() {
        return "pages/community";
    }

    @GetMapping("/written")
    public String writtenPage() {
        return "pages/write-member";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "pages/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "pages/sign-up";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "pages/forgot-password";
    }

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "pages/change-password";
    }
}
