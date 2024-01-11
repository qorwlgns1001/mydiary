package com.example.logindemo.controller;

import com.example.logindemo.dao.MemberDao;
import com.example.logindemo.member.Member;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private MemberDao memberDao = new MemberDao();
    private Member member;

    // ID로 특정 유저 검색
    @GetMapping("/member/{id}")
    public Member findMember(@PathVariable("id") Long id) {
        return memberDao.findById(id);
    }

    @GetMapping("/member/all")
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @PutMapping("/member/put")
    public void saveMember(@RequestParam String loginId, @RequestParam String password, @RequestParam String name, @RequestParam int age) {
        member = new Member(loginId, password, name, age);
        memberDao.saveMember(member);
    }
}
