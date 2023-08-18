package com.project.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Getter
@AllArgsConstructor
public class MessageDto {

    private String message;             // 사용자 전달 메세지
    private String redirectUri;         // 리다이렉트 uri
    private RequestMethod method;       // Http 요청 메서드
    private Map<String, Object> data;   // 화면으로 전달할 데이터

}
