package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Posts;
import com.example.demo.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor  //final이 선언된 모든 필드를 인자값으로하는 생성자
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    
    //글 등록
    @Transactional  //DB수정하는 함수는 transactionl 어노테이션 붙여주기
    public Long AddOrderPosts(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //글 수정
    @Transactional
    public Long ModifyOrderPosts(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    //글 리스트
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> ListPosts(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    
    //특정 게시글 불러오기
    public PostsResponseDto FindOrderPosts(Long id){  //클라이언트가 요구한 특정 게시물을 보내서 응답하기.
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));  //id로 찾아서 담기.
        return new PostsResponseDto(entity);   //찾은 객체를 응답dto로 생성해서 보내기
    }

    //특정 게시글 삭제
    @Transactional
    public void RemoveOrderPosts(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));  //id로 찾아서 담기
        postsRepository.delete(posts);
    }

    //좋아요 누르기
    @Transactional
    public Long AddOrderLike(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.updateLike(posts.getLikecount() + 1);
        return posts.getLikecount();
    }

    //좋아요 취소
    @Transactional
    public Long RemoveOrderLike(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        if (posts.getLikecount() > 0){
            posts.updateLike(posts.getLikecount()-1);
        }else{
            posts.updateLike(0L);
        }
        return posts.getLikecount();
    }

    //조회수 증가
    @Transactional
    public Long AddOrderView(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.updateView(posts.getViewcount() + 1);
        return posts.getViewcount();
    }
}
