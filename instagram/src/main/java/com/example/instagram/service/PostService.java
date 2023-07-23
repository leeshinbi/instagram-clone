package com.example.instagram.service;

// PostService.java

import com.example.instagram.entity.Post;
import com.example.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Long createPost(Post post) {
        Post newPost = postRepository.save(post);
        return newPost.getId();
    }


    public Long updatePost(Long id, Post post) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setPhotos(post.getPhotos());
            existingPost.setContents(post.getContents());
            postRepository.save(existingPost);

            return existingPost.getId(); // Post 객체의 id를 반환
        }

        return null;
    }

    public Boolean delete(long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
