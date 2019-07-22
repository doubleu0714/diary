package io.doubleu0714.diary.service.impl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.doubleu0714.diary.dto.MemberDTO;
import io.doubleu0714.diary.entity.Member;
import io.doubleu0714.diary.repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceImplTest {
    @MockBean(name = "memberService")
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
}