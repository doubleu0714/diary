package io.doubleu0714.diary.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.Value;

/**
 * <h1>일기 엔터티</h1>
 * 회원은 본인의 일기를 CRUD 할 수 있고, 비밀일기로 설정 할 수 있다.<br/>
 * 공개 일기의 경우 모든 회원들에게 노출 됩니다.<br/>
 * 다른 사람의 일기를 스크랩 할 수 있습니다.<br/>
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class Diary extends BaseInfo {
    /**
     * 자동 생성된 아이디
     */
    @Id
    @GeneratedValue
    private long diaryId;

    /**
     * 일기 내용
     */
    @Column(length = 2000, nullable = false)
    private String diaryContent;

    /**
     * 스크랩된 횟수
     */
    @Column
    private int scrapCnt = 0;

    /**
     * 공개여부
     */
    @Column
    private boolean privateDiary;

    /**
     * 일기 작성자 회원
     */
    @Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Member member;

    /**
     * 일기에 등록한 해쉬태그 목록
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "diary")
    private List<HashTag> hashTags;
}