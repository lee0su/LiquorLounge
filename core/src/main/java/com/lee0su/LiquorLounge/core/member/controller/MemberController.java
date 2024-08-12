package com.lee0su.LiquorLounge.core.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/main")
    public String memberMainPage() {
        return "member/main";
    }

    @GetMapping("/liquor")
    public String memberLiquorPage() {
        return "member/liquor";
    }

    @GetMapping("/community")
    public String memberCommunityPage() {
        return "member/community";
    }

}
