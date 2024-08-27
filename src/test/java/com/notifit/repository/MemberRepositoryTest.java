package com.notifit.repository;

import com.notifit.entity.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    private static final String PASSWORD = "password";

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 Entity 를 저장한다.")
    void saveTest() {
        // given
        String username = "username";
        String name = "name";
        String phoneNumber = "phoneNumber";
        Member member = createMember(username, name, phoneNumber);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getUsername()).isEqualTo(username);
        assertThat(savedMember.getPassword()).isEqualTo(PASSWORD);
        assertThat(savedMember.getName()).isEqualTo(name);
        assertThat(savedMember.getPhoneNumber()).isEqualTo(phoneNumber);
    }
    
    @Test
    @DisplayName("동일한 회원 ID 가 있는지 조회한다.")
    void existsByUsernameTest() {
        // given
        String duplicatedUsername = "duplicatedUsername";
        String username = "username";
        Member duplicatedMember = createMember(duplicatedUsername, "name", "phoneNumber");
        memberRepository.save(duplicatedMember);

        // when
        boolean isDuplicated = memberRepository.existsByUsername(duplicatedUsername);
        boolean isExist = memberRepository.existsByUsername(username);

        // then
        assertThat(isDuplicated).isTrue();
        assertThat(isExist).isFalse();
    }

    private Member createMember(String username, String name, String phoneNumber) {
        return Member.of(username, PASSWORD, name, phoneNumber, true, true);
    }
}