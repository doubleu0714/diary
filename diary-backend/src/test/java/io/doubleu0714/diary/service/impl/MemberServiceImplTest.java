package io.doubleu0714.diary.service.impl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringRunner;

import io.doubleu0714.diary.dto.MemberDTO;
import io.doubleu0714.diary.entity.Member;
import io.doubleu0714.diary.repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceImplTest {
    @Autowired
    private MemberServiceImpl memberService;

    @MockBean(name = "memberRepository")
    private MemberRepository memberRepository;

    @Test
    public void givenValidData_whenSaveMember() {
        // given
        String memberId = "TEST_ID";
        String memberName = "memberName";
        String password = "password";
        String email = "email@gmail.com";
        MemberDTO dto = MemberDTO.of(memberId, memberName, password, email);
        Member member = Member.builder().memberId(memberId).memberName(memberName).password(password).email(email).build();
        when(memberRepository.save(member)).thenReturn(member);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        // when
        memberService.saveMember(dto);

        // then
        MemberDTO savedDto = memberService.getMember(memberId);
        assertThat(savedDto, notNullValue());
        assertThat(savedDto, is(dto));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyMemberId_whenSaveMember() {
        // given
        String memberId = "";
        String memberName = "memberName";
        String password = "password";
        String email = "email@gmail.com";
        MemberDTO dto = MemberDTO.of(memberId, memberName, password, email);

        // when
        memberService.saveMember(dto);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullMemberId_whenSaveMember() {
        // given
        String memberId = null;
        String memberName = "memberName";
        String password = "password";
        String email = "email@gmail.com";
        MemberDTO dto = MemberDTO.of(memberId, memberName, password, email);

        // when
        memberService.saveMember(dto);

        // then
    }

    @Test
    public void 회원_목록조회_테스트() {
        // given
        int pageNo = 1;
        List<Member> memberList = new ArrayList<>();
        memberList.add(Member.builder().memberId("memberId01").email("email01").memberName("memberName01").build());
        memberList.add(Member.builder().memberId("memberId02").email("email02").memberName("memberName02").build());
        memberList.add(Member.builder().memberId("memberId03").email("email03").memberName("memberName03").build());
        memberList.add(Member.builder().memberId("memberId04").email("email04").memberName("memberName04").build());
        memberList.add(Member.builder().memberId("memberId05").email("email05").memberName("memberName05").build());
        memberList.add(Member.builder().memberId("memberId06").email("email06").memberName("memberName06").build());
        memberList.add(Member.builder().memberId("memberId07").email("email07").memberName("memberName07").build());
        memberList.add(Member.builder().memberId("memberId08").email("email08").memberName("memberName08").build());
        memberList.add(Member.builder().memberId("memberId09").email("email09").memberName("memberName09").build());
        memberList.add(Member.builder().memberId("memberId10").email("email10").memberName("memberName10").build());
        Page<Member> returnValue = new PageImpl<>(memberList);
        when(memberRepository.findAll(PageRequest.of(pageNo - 1, 10, Sort.by(Order.desc("modifiedAt"))))).thenReturn(returnValue);

        // when
        List<MemberDTO> list = memberService.getMembers(pageNo);

        // then
        List<String> memberIds = list.stream().map(dto -> dto.getMemberId()).collect(Collectors.toList());
        List<String> givenIds = memberList.stream().map(member -> member.getMemberId()).collect(Collectors.toList());
        assertThat(Arrays.toString(memberIds.toArray()), is(Arrays.toString(givenIds.toArray())));
        verify(memberRepository).findAll(PageRequest.of(pageNo - 1, 10, Sort.by(Order.desc("modifiedAt"))));
        verify(memberRepository, times(1)).findAll(PageRequest.of(pageNo - 1, 10, Sort.by(Order.desc("modifiedAt"))));
    }
}