package io.doubleu0714.diary.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.doubleu0714.diary.entity.Member;

public interface MemberRepository extends PagingAndSortingRepository<Member, String> {
    
}