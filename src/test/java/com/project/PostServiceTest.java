package com.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.domain.post.PostRequest;
import com.project.domain.post.PostResponse;
import com.project.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void save(){
        PostRequest params=new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("MS2");
        params.setNoticeYn(false);
        Long id=postService.savePost(params);
        System.out.println("생성된 게시글 ID: "+id);
    }

    @Test
    void findById(){
        PostResponse post= postService.findPostById(1L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void update(){
        PostRequest params=new PostRequest();
        params.setId(1L);
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("MS3");
        params.setNoticeYn(true);
        postService.updatePost(params);

        PostResponse post=postService.findPostById(1L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    /*
    @Test
    void delete(){
        System.out.println("삭제 이전의 전체 게시글 수는 : "+postService.findAllPost().size()+"개 입니다.");
        postService.deletePost(2L);
        System.out.println("삭제 이후의 전체 게시글 수는 : "+postService.findAllPost().size()+"개 입니다.");
    }
    */

    @Test
    void saveByForeach(){
        for (int i=1; i<=1000; i++){
            PostRequest params=new PostRequest();
            params.setTitle(i +"번 게시글 제목");
            params.setContent(i + "번 게시글 내용");
            params.setWriter("테스터" + i);
            params.setNoticeYn(false);
            postService.savePost(params);

        }
    }
}
