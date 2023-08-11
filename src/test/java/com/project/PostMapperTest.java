package com.project;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.domain.post.PostMapper;
import com.project.domain.post.PostRequest;
import com.project.domain.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void save(){
        PostRequest params= new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        postMapper.save(params);

        List<PostResponse> posts=postMapper.findAll();
        System.out.println("전체 게시글 개수는 : "+posts.size()+"개입니다.");
    }

    @Test
    void findById(){
        PostResponse post= postMapper.findById(1L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void update(){
        // 게시글 수정
        PostRequest params=new PostRequest();
        params.setId(1L);
        params.setTitle("1번 게시글 제목 수정");
        params.setContent("1번 게시글 내용 수정");
        params.setWriter("MS");
        params.setNoticeYn(true);
        postMapper.update(params);

        //게시글 상세 정보 조회
        PostResponse post=postMapper.findById(1L);
        try{
            String postJson= new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }

    @Test
    void delete(){
        System.out.println("삭제 이전의 전체 게시글 수는 : "+postMapper.findAll().size()+"개 입니다.");
        postMapper.deleteById(1L);
        System.out.println("삭제 이후의 전체 게시글 수는 : "+postMapper.findAll().size()+"개 입니다.");
    }
}