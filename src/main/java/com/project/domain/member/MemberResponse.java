package com.project.domain.member;


import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private Long id;                        // 회원 번호(PK)
    private String loginId;                 // 로그인 ID
    private String password;                // 비밀 번호
    private String name;                    // 이름
    private Gender gender;                  // 성별
    private LocalDate birthday;             // 생일
    private Boolean deleteYn;               // 삭제 여부
    private LocalDateTime createdDate;      // 생성 일시
    private LocalDateTime modifiedDate;     // 최종 수정 일시

    public void clearPassword(){
        this.password="";
    }
}
