package com.lee0su.LiquorLounge.core.controller.web;

import com.lee0su.LiquorLounge.core.entity.community.Post;
import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import com.lee0su.LiquorLounge.core.service.community.PostService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/pages")
@Controller
public class PagesController {
    @Autowired
    private PostService postService;

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
    public String communityPage(Model model) {
        // Model을 사용하면 컨트롤러에서 서비스나 데이터베이스로부터 가져온 데이터를 뷰(템플릿)로 쉽게 전송 가능
        List<Post> posts = postService.getAllPosts(); // 게시글 목록 가져오기
        model.addAttribute("posts", posts); // 모델에 게시글 리스트 추가
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
