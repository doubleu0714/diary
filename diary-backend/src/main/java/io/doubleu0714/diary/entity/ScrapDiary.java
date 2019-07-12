package io.doubleu0714.diary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h1>스크랩정보</h1>
 * 다른 회원의 일기를 스크랩한 정보를 저장하는 엔터티
 */
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Diary diary;

    /**
     * 스크랩한 회원
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Member member;
}