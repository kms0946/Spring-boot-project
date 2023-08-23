package com.project.domain.post;

import com.project.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    void save(PostRequest params);

    PostResponse findById(Long id);

    void update(PostRequest params);

    void deleteById(Long id);

    // 게시글 리스트 조회
    List<PostResponse> findAll(SearchDto params);

    // 게시글 수 카운팅
    int count(SearchDto params);
}
