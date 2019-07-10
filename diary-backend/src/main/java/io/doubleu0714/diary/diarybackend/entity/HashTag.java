package io.doubleu0714.diary.diarybackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * <h1>일기에 등록한 해쉬태그</h1>
 * 등록한 해쉬태그로 조회 할 수 있다.
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class HashTag extends BaseInfo {
    /**
     * 자동생성 아이디
     */
    @Id
    @GeneratedValue
    private long hashTagId;

    /**
     * 사용자가 입력한 해쉬태그<br/>
     * '#'로 시작해서 띄어쓰기 혹은 '#' 까지의 문자열
     */
    @Column(length = 50, nullable = false)
    private String hashTag;

    /**
     * 대상 일기
     */
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Diary diary;
}