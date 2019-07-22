package io.doubleu0714.diary.repository;

import org.springframework.data.repository.CrudRepository;

import io.doubleu0714.diary.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
    
}