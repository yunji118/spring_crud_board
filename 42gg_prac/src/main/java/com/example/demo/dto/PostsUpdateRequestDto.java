package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto { //post를 업데이트하는 요청에 쓰는 dto
    /*Posts entity의 column 중에 쓸 것만 가져오기*/
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
