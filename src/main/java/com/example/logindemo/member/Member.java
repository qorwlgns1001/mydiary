package com.example.logindemo.member;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
    private long Id; // 고유 키
    @NonNull
    private String loginId; // 유저 아이디
    @NonNull
    private String password; // 유저 패스워드
    @NonNull
    private String name; // 유저 이름
    @NonNull
    private int age; // 유저 나이a
    private LocalDateTime createTime; // 생성 시간
}
