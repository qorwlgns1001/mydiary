package com.example.logindemo.member;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "ID")
    private long Id; // 고유 키
    @Column(name = "LOGINID", nullable = false)
    private String loginId; // 유저 아이디
    @Column(name = "PASSWORD", nullable = false)
    private String password; // 유저 패스워드
    @Column(name = "NAME", nullable = false)
    private String name; // 유저 이름
    @Column(name = "AGE", nullable = false)
    private int age; // 유저 나이
    @Column(name = "CREATETIME")
    private LocalDateTime createTime; // 생성 시간
}
