package com.project.domain.comment;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 클래스의 기본 생성자 생성, 객체 속성 protected, JSON 포맷으로 데이터 전달
public class CommentRequest {

    private Long id;            // 댓글 번호(PK)
    private Long postId;        // 게시글 번호(FK)
    private String content;     // 내용
    private String writer;      // 작성자
}
