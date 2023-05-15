package com.example.demo.dto;

import com.example.demo.entity.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {  //클라이언트에게 응답.
    private Long id;
    private String title;
    private String content;
    private String author;
    private Long likecount;
    private Long viewcount;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.likecount = entity.getLikecount();
        this.viewcount = entity.getViewcount();
    }
}
