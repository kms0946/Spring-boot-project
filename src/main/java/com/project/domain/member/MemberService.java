package com.project.domain.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    /*
    * 회원 정보 저장(회원 가입)
    * */
    @Transactional
    public Long saveMember(final MemberRequest params){
        params.encodingPassword(passwordEncoder);
        memberMapper.save(params);
        return params.getId();
    }

    /*
    * 회원 상세 정보 조회
    * */
    public MemberResponse findMemberByLoginId(final String loginId){
        return memberMapper.findByLoginId(loginId);
    }

    /*
    * 회원 정보 수정
    * */
    @Transactional
    public Long updateMember(final MemberRequest params){
        params.encodingPassword(passwordEncoder);
        memberMapper.update(params);
        return params.getId();
    }

    /*
    * 회원 정보 삭제
    * */
    @Transactional
    public Long deleteMember(final Long id){
        memberMapper.deleteById(id);
        return id;
    }

    /*
    *회원 수 카운팅(ID 중복 체크)
    * */
    public int countMemberByLoginId(final String loginId){
        return memberMapper.countByLoginId(loginId);
    }

    /*
    * 로그인 로직
    * */
    public MemberResponse login(final String loginId, final String password) {

        // 회원 정보 및 비밀번호 조회
        MemberResponse member = findMemberByLoginId(loginId);
        String encodedPassword = (member == null) ? "" : member.getPassword();

        // 2. 회원 정보 및 비밀번호 체크
        if (member == null || passwordEncoder.matches(password, encodedPassword) == false) {
            return null;
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        member.clearPassword();
        return member;
    }
}
