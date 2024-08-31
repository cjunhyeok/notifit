package com.notifit.service;

import com.notifit.controller.dtos.member.JoinRequest;
import com.notifit.entity.member.Member;
import com.notifit.exception.CustomException;
import com.notifit.exception.ErrorCode;
import com.notifit.repository.MemberRepository;
import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 정보를 이용해 회원가입을 진행한다.")
    void joinTest() {
        // given
        JoinRequest request = createJoinRequest("username", "password");

        // when
        Long savedMemberId = memberService.join(request);

        // then
        Member findMember = memberRepository.findById(savedMemberId).get();
        assertThat(findMember.getId()).isEqualTo(savedMemberId);
    }

    @Test
    @DisplayName("회원가입 시 사용자 ID 가 중복된 경우 예외가 발생한다.")
    void joinDuplicatedTest() {
        // given
        JoinRequest request = createJoinRequest("username", "password");
        memberService.join(request);
        JoinRequest joinRequest = createJoinRequest("username", "password");

        // when
        AbstractObjectAssert<?, CustomException> extracting = assertThatThrownBy(() -> memberService.join(joinRequest))
                .isInstanceOf(CustomException.class)
                .extracting(ex -> (CustomException) ex);

        // then
        extracting.satisfies(ex -> {
            assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.DUPLICATED_USERNAME);
        });
    }

    private JoinRequest createJoinRequest(String username, String password) {
        return JoinRequest
                .builder()
                .username(username)
                .password(password)
                .name("name")
                .phoneNumber("010-1234-5678")
                .isTermOfUseCheck(true)
                .isPrivacyCheck(true)
                .build();
    }
}
