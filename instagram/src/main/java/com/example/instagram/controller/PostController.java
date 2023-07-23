package com.example.instagram.controller;

import com.example.instagram.entity.Post;
import com.example.instagram.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping //R: READ 'ALL' 전체를 조회
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}") //R : READ 'ONE' 하나를 조회
    //만약 게시물이 존재하지 않을 수도 있는 상황에서 사용한다면 Optional을 사용
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping ("write") // 포스트 생성
    @ResponseStatus(HttpStatus.CREATED) // Created 상태 코드 (201) 반환
    public Long createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}") //특정 'id' 게시물 수정
    public Long updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }


    @DeleteMapping("/{id}") //특정 'id' 게시물 삭제
    public Boolean delete(@PathVariable long id) {
        return postService.delete(id);
    }
}