package com.example.instagram.controller;

import com.example.instagram.entity.Member;
import com.example.instagram.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입
    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String name) {
        if (memberService.isUsernameAvailable(username)) {
            //isUsernameAvailable를 사용하여, username이 이미 있으면 'false', 없으면 'true'반환
            memberService.signUp(username, password, name);
            return "회원가입 성공!";
        }
        return "이미 가입한 회원입니다.";
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Member member = memberService.login(username, password);
        if (member != null) {
            //member가 null이 아니면 '로그인 성공' 반환
            return "로그인 성공!";
        }
        return "로그인 실패";
    }
}
