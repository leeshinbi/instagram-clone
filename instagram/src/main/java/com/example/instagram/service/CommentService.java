package com.example.instagram.service;

import com.example.instagram.entity.Comment;
import com.example.instagram.entity.Member;
import com.example.instagram.entity.Post;
import com.example.instagram.repository.CommentRepository;
import com.example.instagram.repository.MemberRepository;
import com.example.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository; // PostRepository 주입 추가
    private final MemberRepository memberRepository; // MemberRepository 주입 추가

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    // 댓글 생성
    public Comment createComment(String content, Long postId, Long memberId) {
        // 사용자가 입력한 내용(content), 게시물의 id(postId), 사용자의 id(memberId)를
        // 파라미터로 받아서 새로운 Comment 인스턴스를 생성
        Comment comment = new Comment();
        comment.setContent(content);

        // postId를 사용하여 Post 객체를 가져와서 Comment에 설정
        Post post = postRepository.findById(postId).orElse(null);
        comment.setPost(post);

        // memberId를 사용하여 Member(User) 객체를 가져와서 Comment에 설정
        Member member = memberRepository.findById(memberId).orElse(null);
        comment.setMember(member);

        return commentRepository.save(comment);
    }


    // 댓글 조회
    public Optional<Comment> getCommentById(Long id) {
        // 특정 댓글의 id를 파라미터로 받아서 해당 id에 해당하는 댓글을 조회
        return commentRepository.findById(id);
    }

    // 댓글 수정
    public Comment updateComment(Long id, String content) {
        // 특정 댓글의 id와 수정할 내용(content)을 파라미터로 받아서 해당 댓글의 내용을 수정
        Optional<Comment> optionalComment = commentRepository.findById(id);
        // 해당 id에 해당하는 댓글을 찾고
        if (optionalComment.isPresent()) { // 해당 댓글이 존재하는지 확인하고, 존재하면 해당 댓글의 내용을 수정
            Comment comment = optionalComment.get();
            comment.setContent(content);
            return commentRepository.save(comment);
        }
        return null;
    }

    // 댓글 삭제
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            // 해당 id에 해당하는 댓글이 존재하는지 확인하고, 존재하면 삭제
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
