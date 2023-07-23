package com.example.instagram.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //식별자 id
    private Long id;

    //회원이 입력하는 id=username
    @Column(length = 20,unique = true, nullable = false)
    private String username;

    @Column(length = 20,nullable = false)
    private String password;

    @Column(length = 20,nullable = false)
    private String name;

    // 생성자, 게터, 세터 등 필요한 코드 추가
}
