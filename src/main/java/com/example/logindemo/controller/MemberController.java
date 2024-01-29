package com.example.logindemo.controller;

import com.example.logindemo.member.Member;
import com.example.logindemo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private Member member;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // ID로 특정 유저 검색
    @GetMapping("/member/{id}")
    public Optional<Member> findMember(@PathVariable("id") Long id) {
        return memberRepository.findById(id);
    }

    @GetMapping("/member/all")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @PutMapping("/member/put")
    public void saveMember(@RequestParam String loginId, @RequestParam String password, @RequestParam String name, @RequestParam int age) throws Exception {
        member = new Member(loginId, password, name, age, LocalDateTime.now());
        memberRepository.save(member);
    }
}
