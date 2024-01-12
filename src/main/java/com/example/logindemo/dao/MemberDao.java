package com.example.logindemo.dao;

import com.example.logindemo.member.Member;

import java.time.LocalDateTime;
import java.util.*;

public class MemberDao {
    private static long nextId = 0;

    private Map<Long, Member> map = new HashMap<Long, Member>();
    private Set<String> loginIdSet = new HashSet<>();

    public Member findById(Long id) {
        return map.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }

    public void saveMember(Member member) throws Exception {
        if (loginIdSet.contains(member.getLoginId())) {
            throw new Exception("아이디가 중복되었습니다.");
        }
        loginIdSet.add(member.getLoginId());
        member.setId(++nextId);
        member.setCreateTime(LocalDateTime.now());
        map.put(member.getId(), member);
    }

}
