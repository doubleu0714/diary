package io.doubleu0714.diary.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import io.doubleu0714.diary.dto.MemberDTO;
import io.doubleu0714.diary.entity.Member;
import io.doubleu0714.diary.repository.MemberRepository;
import io.doubleu0714.diary.service.MemberService;
import io.doubleu0714.diary.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void saveMember(MemberDTO dto) {
        log.info("CALL MemberServiceImpl.saveMember. param: {dto: {}}", dto);
        Member member = Member
                            .builder()
                            /* 회원 아이디 */
                            .memberId(
                                Optional.ofNullable(dto).map(MemberDTO::getMemberId).filter(StringUtils::isNotEmpty).orElseThrow(() -> {
                                    log.error("잘못된 회원 아이디 입니다.");
                                    return new IllegalArgumentException("잘못된 회원 아이디 입니다.");
                                })
                            )
                            /* 회원 이름 */
                            .memberName(
                                Optional.ofNullable(dto).map(MemberDTO::getMemberName).filter(StringUtils::isNotEmpty).orElseThrow(() -> {
                                    log.error("잘못된 회원 이름 입니다.");
                                    return new IllegalArgumentException("잘못된 회원 이름 입니다.");
                                })
                            )
                            /* 회원 비밀번호 */
                            .password(
                                Optional.ofNullable(dto).map(MemberDTO::getPassword).filter(StringUtils::isNotEmpty).orElseThrow(() -> {
                                    log.error("잘못된 비밀번호 입니다.");
                                    return new IllegalArgumentException("잘못된 비밀번호 입니다.");
                                })
                            )
                            /* 회원 이메일 */
                            .email(
                                Optional.ofNullable(dto).map(MemberDTO::getEmail).filter(StringUtils::isNotEmpty).orElseThrow(() -> {
                                    log.error("잘못된 이메일 주소 입니다.");
                                    return new IllegalArgumentException("잘못된 이메일 주소 입니다.");
                                })
                            )
                            .build();
        memberRepository.save(member);
    }

    @Override
    public MemberDTO getMember(String memberId) {
        log.info("CALL MemberServiceImpl.getMember. param: {memberId: {}}", memberId);
        try {
            Member entity = memberRepository.findById(memberId).get();
            return MemberDTO.of(entity.getMemberId(), entity.getMemberName(), entity.getPassword(), entity.getEmail());
        } catch(NoSuchElementException e) {
            log.error("회원 정보가 없습니다.", e);
            throw new NoSuchElementException("회원 정보가 없습니다.");
        }
    }

    @Override
    public List<MemberDTO> getMembers(int pageNo) {
        log.info("CALL MemberServiceImpl.getMembers. param: {pageNo: {}}", pageNo);
        if(pageNo <= 0) throw new IllegalArgumentException("잘못된 페이지 입니다.");
        return memberRepository
                .findAll(PageRequest.of(pageNo - 1, 10, Sort.by(Order.desc("modifiedAt"))))
                .map(entity -> MemberDTO.of(entity.getMemberId(), entity.getMemberName(), entity.getPassword(), entity.getEmail()))
                .getContent();
	}
}