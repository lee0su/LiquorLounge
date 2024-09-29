package com.lee0su.LiquorLounge.core.controller.community;

import com.lee0su.LiquorLounge.core.entity.community.Post;
import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import com.lee0su.LiquorLounge.core.service.community.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // 게시글 목록 페이지
    @GetMapping
    public String showPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "pages/community"; // community.html로 이동
    }

    // 게시글 작성 페이지
    @GetMapping("/new")
    public String showCreatePostForm() {
        return "pages/write-member"; // write-member.html로 이동
    }

    // 게시글 등록 처리
    @PostMapping
    public String createPost(@RequestParam String title,
                             @RequestParam String content,
                             @RequestParam(required = false) MultipartFile image,
                             HttpSession session) {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        UserEntity loggedInUser = (UserEntity) session.getAttribute("user");

        if (loggedInUser == null) {
            // 사용자 로그인 상태가 아니면 에러 처리 또는 리디렉션
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriteDate(LocalDate.now());

        // username을 사용하여 savePost 호출
        postService.savePost(post, image, loggedInUser.getName());

        return "redirect:/posts"; // 등록 후 게시글 목록 페이지로 리디렉션
    }

}
