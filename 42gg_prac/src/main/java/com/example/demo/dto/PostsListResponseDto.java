package com.example.demo.dto;

import com.example.demo.entity.Posts;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;
    private Long likecount;
    private Long viewcount;

    //기존에 있던 값들을 정렬(PostsListResponseDto), 보여주기(PostsResponseDto)는 @Build 어노테이션 사용X. 값 넣어주는 애들만 @build 사용
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.likecount = entity.getLikecount();
        this.viewcount = entity.getViewcount();
    }
}
