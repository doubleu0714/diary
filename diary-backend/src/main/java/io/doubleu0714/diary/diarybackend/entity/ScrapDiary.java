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
 * <h1>스크랩정보</h1>
 * 다른 회원의 일기를 스크랩한 정보를 저장하는 엔터티
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class ScrapDiary extends BaseInfo {
    /**
     * 자동 생성된 아이디
     */
    @Id
    @GeneratedValue
    private long scrapId;

    /**
     * 스크램시 작성한 일기에 대한 자기평
     */
    @Column(length = 1000)
    private String scrapComment;

    /**
     * 스크랩한 diary
     */
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Diary diary;

    /**
     * 스크랩한 회원
     */
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Member member;
}