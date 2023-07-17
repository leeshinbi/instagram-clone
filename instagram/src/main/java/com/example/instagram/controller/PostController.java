package com.example.instagram.controller;

// PostController.java

import com.example.instagram.entity.Post;
import com.example.instagram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping //R: READ 'ALL' 전체를 조회
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}") //R : READ 'ONE' 하나를 조회
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping // 포스트 생성
    @ResponseStatus(HttpStatus.CREATED) // Created 상태 코드 (201) 반환
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}") //특정 'id' 게시물 수정
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/{id}") //특정 'id' 게시물 삭제
    public void deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
