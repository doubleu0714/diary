package io.doubleu0714.diary.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.ToString;

/**
 * <h1>기본 엔터티</h1>
 * 모든 엔터티가 가져야할 기본 멤버<br/>
 * 생성일, 수정일을 자동을 셋팅할 수 있도록 한다.
 */
@Getter
@ToString
@MappedSuperclass
public class BaseInfo {
    /**
     * 엔터티 생성일시
     */
    @Column
    private LocalDateTime createdAt;

    /**
     * 엔터티 수정일시
     */
    @Column
    private LocalDateTime modifiedAt;

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
}