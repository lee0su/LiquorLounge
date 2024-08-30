package com.lee0su.LiquorLounge.core.controller.web;

import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/main")
    public String memberMainPage(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest/main";
        }
        return "member/main";
    }

    @GetMapping("/liquor")
    public String memberLiquorPage(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest/main";
        }
        return "member/liquor";
    }

    @GetMapping("/community")
    public String memberCommunityPage(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest/main";
        }
        return "member/community";
    }

    @GetMapping("/written")
    public String memberWrittenPage(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest/write-guest";
        }
        return "member/write-member";
    }

    @GetMapping("/recommend/whiskey")
    public String recommendWhiskeyPage() {
        return "member/recommend-whiskey";
    }

    @GetMapping("/recommend/cocktail")
    public String recommendCocktailPage() {
        return "member/recommend-cocktail";
    }
}
