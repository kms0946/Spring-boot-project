package com.project.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /*
    * 회원 정보 저장(회원 가입)
    * */
    void save(MemberRequest params);

    /*
    * 회원 상세 정보
    * */
    MemberResponse findByLoginId(String loginId);

    /*
    * 회원 정보 수정
    * */
    void update(MemberRequest params);

    /*
    * 회원 정보 삭제
    * */
    void deleteById(Long id);

    /*
    * 회원 수 카운팅
    * */
    int countByLoginId(String loginId);
}
