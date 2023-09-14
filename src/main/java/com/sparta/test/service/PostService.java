package com.sparta.test.service;

import com.sparta.test.dto.DeleteReponseDto;
import com.sparta.test.dto.PostRequestDto;
import com.sparta.test.dto.PostResponseDto;
import com.sparta.test.entity.Post;
import com.sparta.test.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;


    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);

        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;

    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostById(Long postId) {
        Optional<Post> findbyId = postRepository.findById(postId);
        if(findbyId.isPresent()){
            Post post = findbyId.get();
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else{
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = finePost(postId);
        if(post == null){
            postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
            );
        }
        post.update(postRequestDto);
        Post updatePost = postRepository.save(post);
        return new PostResponseDto(updatePost);
    }

    public DeleteReponseDto deletePost(Long postId) {
        Post post = finePost(postId);
        if(post == null){
            postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
            );
        }
        postRepository.deleteById(postId);
        return new DeleteReponseDto("삭제 완료");
    }

    public Post finePost(Long id){
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }
}
