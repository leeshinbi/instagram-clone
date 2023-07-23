package com.example.instagram.service;

import com.example.instagram.entity.Member;
import com.example.instagram.repository.MemberRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isUsernameAvailable(String username) {
        //username이 이미 있다면 'false', 없다면 'true' 반환
        return memberRepository.findByUsername(username) == null;
    }

    public Member signUp(String username, String password, String name) {
        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(password);
        newMember.setName(name);
        return memberRepository.save(newMember);
    }

    public Member login(String username, String password) {
        Member member = memberRepository.findByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            //조회된 member가 null이 아니고, 해당 member의 비밀번호(password)가
            //입력한 password와 일치하면 아이디와 비밀번호가 올바른 것으로 판단
            return member;
        }
        return null;
    }
}
