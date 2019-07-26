package io.doubleu0714.diary.service;

import java.util.List;
import java.util.NoSuchElementException;

import io.doubleu0714.diary.dto.MemberDTO;

public interface MemberService {
    /**
     * 회원 정보를 저장하는 메소드.
     * @param dto {@link MemberDTO} 회원정보객체
     * @throw {@link IllegalArgumentException} 회원정보객체의 멤버 중 유효하지 않은 값이 존재할 때.
     */
    void saveMember(MemberDTO dto);

    /**
     * 아이디에 해당하는 회원 정보를 조회하는 메소드.
     * @param memberId 회원 아이디
     * @return {@link MemberDTO} 회원정보 객체
     * @throw {@link NoSuchElementException} 아이디에 해당하는 회원정보가 없을 때.
     */
    MemberDTO getMember(String memberId);

    /**
     * 페이지에 해당하는 회원 정보 목록을 조회하는 메소드<br/>
     * 페이지당 출력 갯수등 페이지 관련 상수값은 application.yml 파일에 정의
     * @param pageNo 페이지 번호
     * @return {@link MemberDTO} 회원정보 목록
     * @throw {@link NoSuchElementException} 페이지에 해당하는 회원목록이 없을 때.
     */
    List<MemberDTO> getMembers(int pageNo);
}