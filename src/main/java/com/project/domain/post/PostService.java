package com.project.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 클래스 내 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class PostService {

    private final PostMapper postMapper;

    @Transactional // 선언적 트랜잭션 기능, 메서드의 실행과 동시에 트랜잭션이 시작되며, 정상 종료 여부에 따라 commit or rollback
    //게시글 저장
    public Long savePost(final PostRequest params){
        postMapper.save(params);
        return params.getId();
    }

    //게시글 상세 정보
    public PostResponse findPostById(final Long id){
        return postMapper.findById(id);
    }

    @Transactional
    //게시글 수정
    public Long updatePost(final PostRequest params){
        postMapper.update(params);
        return params.getId();
    }

    //게시글 삭제
    public Long deletePost(final Long id){
        postMapper.deleteById(id);
        return id;
    }

    //게시글 리스트 조회
    public List<PostResponse> findAllPost(){
        return postMapper.findAll();
    }


}
