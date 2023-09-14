package com.sparta.test.controller;

import com.sparta.test.dto.DeleteReponseDto;
import com.sparta.test.dto.PostRequestDto;
import com.sparta.test.dto.PostResponseDto;
import com.sparta.test.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }


    //게시글 상세 조회
    @GetMapping("/post/{postId}")
    public PostResponseDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }



    //게시글 전체조회
    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    //게시글 수정
    @PutMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){
       PostResponseDto updatePost = postService.updatePost(postId, postRequestDto);
       return updatePost;
    }


    //게시글 삭제
    @DeleteMapping("/post/{postId}")
    public DeleteReponseDto deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }

}
