package com.example.logindemo.member;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id; // 고유 키
    @NonNull
    @Column(name = "LOGINID", nullable = false)
    private String loginId; // 유저 아이디
    @NonNull
    @Column(name = "PASSWORD", nullable = false)
    private String password; // 유저 패스워드
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name; // 유저 이름
    @NonNull
    @Column(name = "AGE", nullable = false)
    private int age; // 유저 나이
    @NonNull
    @Column(name = "CREATETIME")
    private LocalDateTime createTime; // 생성 시간
}
