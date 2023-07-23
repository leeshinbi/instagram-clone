package com.example.instagram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 800)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    //다대일 관계
    //하나의 댓글은 하나의 게시물에 속하고, 하나의 게시물은 여러 개의 댓글을 가질 수 있음
    //지연 로딩. 관련된 엔티티들을 실제로 사용할 때까지 조회하지 않는다.
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")
    //외래키
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false , referencedColumnName = "id")
    private Member member;

}
