package com.example.logindemo.dao;

import com.example.logindemo.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {
    private static long nextId = 0;

    private Map<Long, Member> map = new HashMap<Long, Member>();

    public Member findById(Long id) {
        return map.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }

    public void saveMember(Member member) {
        member.setId(++nextId);
        member.setCreateTime(LocalDateTime.now());
        map.put(member.getId(), member);
    }
}
