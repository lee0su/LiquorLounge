package com.lee0su.LiquorLounge.core.entity.community;

import com.lee0su.LiquorLounge.core.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


// JPA를 사용하고 있으므로 애플리케이션이 시작될 때 자동으로 테이블이 생성됨
@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    private boolean isNotice;
    private String title;

    private String author;
    private Long react_count = 0L;;
    private String content;
    private Long likeCount = 0L;;
    private Long readCount = 0L;;
    private LocalDate writeDate;
    private String React = "";
}