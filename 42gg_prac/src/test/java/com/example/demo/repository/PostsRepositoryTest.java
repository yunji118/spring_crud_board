package com.example.demo.repository;

import com.example.demo.entity.Posts;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach   //각각의 테스트 종료 후 실행
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("repotest@naver.com")
                .build());   //likecount = 0, viewcount = 0 으로 기본 생성, modifieddate, createddate 자동 생성

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);    //list에 저장된 posts 중 0번 포스트 가져오기
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
        Assertions.assertThat(posts.getLikecount()).isEqualTo(0);
        Assertions.assertThat(posts.getViewcount()).isEqualTo(0);

    }
}