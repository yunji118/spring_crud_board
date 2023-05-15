package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")    //공통적인 부분은 RequestMapping으로 처리.
public class PostsController {
    private final PostsService postsService;

    /*글 작성하기*/
    @PostMapping("/")
    public Long postsOrderAdd(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.AddOrderPosts(requestDto);
    }

    /*글 보여주기 + 조회수 증가*/
    @GetMapping("/{id}")
    public PostsResponseDto postsOrderDetails(@PathVariable Long id){
        postsService.AddOrderView(id);  //조회수 증가
        return postsService.FindOrderPosts(id);
    }

    /*글 수정*/
    @PutMapping("/{id}")
    public Long postsOrderModify(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.ModifyOrderPosts(id, requestDto);
    }

    /*글 삭제*/
    @DeleteMapping("/{id}")
    public void postsOrderRemove(@PathVariable Long id){
        postsService.RemoveOrderPosts(id);
    }

    /*글 목록*/
    @GetMapping("/")
    public List<PostsListResponseDto> postsOrderList(){
        List<PostsListResponseDto> postsList = postsService.ListPosts();
        return postsList;
    }
    
    /*좋아요*/
    @GetMapping("/{id}/dolike")
    public Long postsAddLike(@PathVariable Long id){
        return postsService.AddOrderLike(id);
    }

    /*좋아요 취소*/
    @GetMapping("/{id}/undolike")
    public Long postsRemoveLike(@PathVariable Long id){
        return postsService.RemoveOrderLike(id);
    }
}
