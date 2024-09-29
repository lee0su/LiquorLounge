package com.lee0su.LiquorLounge.core.repository.community;

import com.lee0su.LiquorLounge.core.entity.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);
    List<Post> findByAuthorContaining(String author); // author가 String이므로 문제 없음
    List<Post> findByContentContaining(String content);
}