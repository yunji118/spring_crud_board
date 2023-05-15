package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert   //insert시 null인 필드 제외
@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity{

    @Id   //테이블의 pk역할
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //기본키 생성을 데이터베이스에 위임(데이터베이스에 의존적)ㄴ
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String author;

    @ColumnDefault("0")
    private Long likecount;

    @ColumnDefault("0")
    private Long viewcount;

    @Builder
    public Posts(String title, String content, String author, Long likecount, Long viewcount){
        this.title = title;
        this.content = content;
        this.author = author;
        this.likecount = likecount;
        this.viewcount = viewcount;
    }

    //Posts 게시글 수정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    //Posts 좋아요
    public void updateLike(Long likecount){
        this.likecount = likecount;
    }
    
    //Posts 조회수 증가
    public void updateView(Long viewcount) {
        this.viewcount = viewcount;
    }
}
