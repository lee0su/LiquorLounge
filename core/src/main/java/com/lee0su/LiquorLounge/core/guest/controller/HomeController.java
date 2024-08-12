package com.lee0su.LiquorLounge.core.guest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class HomeController {

    @GetMapping("/main")
    public String mainPage() {
        return "guest/main";
    }

    @GetMapping("/page2")
    public String Page2() {
        return "guest/page2";
    }

    @GetMapping("/page3")
    public String Page3() {
        return "guest/page3";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "guest/login";
    }

}
