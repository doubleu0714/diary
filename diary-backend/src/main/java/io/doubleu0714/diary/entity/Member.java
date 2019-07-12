package io.doubleu0714.diary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * <h1>회원 엔터티</h1>
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class Member extends BaseInfo {
    /**
     * 회원 아이디
     */
    @Id
    @Column(length = 50, nullable = false, unique = true)
    private String memberId;

    /**
     * 회원 이름
     */
    @Column(length = 50, nullable = false)
    private String memberName;
    
    /**
     * 회원 비밀번호
     */
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 회원 이메일
     */
    @Column(length = 100, nullable = false)
    private String email;
}