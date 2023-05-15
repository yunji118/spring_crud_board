package com.example.demo.dto;

import com.example.demo.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {  //post를 저장하는 요청에 사용하는 dto
    private String title;
    private String content;
    private String author;
    private Long likecount;
    private Long viewcount;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Long likecount, Long viewcount){
        this.title = title;
        this.content = content;
        this.author = author;
        this.likecount = likecount;
        this.viewcount = viewcount;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .likecount(likecount)
                .viewcount(viewcount)
                .build();
    }
}
