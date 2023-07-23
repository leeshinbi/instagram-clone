package com.example.instagram.repository;

import com.example.instagram.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시물의 모든 댓글 조회
    List<Comment> findByPostId(Long postId);
}
