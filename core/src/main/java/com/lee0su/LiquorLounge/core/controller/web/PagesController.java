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
    public String mainPage(HttpSession session) {
        return "pages/main";
    }

    @GetMapping("/recommend-whiskey")
    public String recommendWhiskeyPage(HttpSession session) {
        return "pages/recommend-whiskey";
    }

    @GetMapping("/recommend-cocktail")
    public String recommendCocktailPage(HttpSession session) {
        return "pages/recommend-cocktail";
    }

    @GetMapping("/liquor")
    public String liquorPage(HttpSession session) {
        return "pages/liquor";
    }

    @GetMapping("/community")
    public String communityPage(HttpSession session) {
        return "pages/community";
    }

    @GetMapping("/written")
    public String writtenPage(HttpSession session) {
        return "pages/write-member";
    }

    @GetMapping("/sign-in")
    public String signInPage(HttpSession session) {
        return "pages/sign-in";
    }
}
