package com.lee0su.LiquorLounge.core.service.community;

import com.lee0su.LiquorLounge.core.entity.community.Post;
import com.lee0su.LiquorLounge.core.repository.community.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    private final String uploadDir = "src/main/resources/static/data"; // 파일 저장 경로 설정

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void savePost(Post post, MultipartFile image, String username) {
        // username을 author에 설정
        post.setAuthor(username);

        if (image != null && !image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path path = Paths.get(uploadDir, fileName);
                Files.write(path, image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        postRepository.save(post);
    }

    public List<Post> searchPosts(String criteria, String value) {
        switch (criteria) {
            case "title":
                return postRepository.findByTitleContaining(value);
            case "author":
                return postRepository.findByAuthorContaining(value); // author가 String이므로 수정
            case "content":
                return postRepository.findByContentContaining(value);
            default:
                return List.of();
        }
    }
}
