package io.doubleu0714.diary.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * <h1>회원 정보</h1>
 * 클라이언트에서 받아오는 회원 정보를 저장하는 객체<br/>
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode
@ToString
public class MemberDTO {
    /**
     * 회원 아이디
     */
    private String memberId;

    /**
     * 회원 이름
     */
    private String memberName;
    
    /**
     * 회원 비밀번호
     */
    private String password;

    /**
     * 회원 이메일
     */
    private String email;
}