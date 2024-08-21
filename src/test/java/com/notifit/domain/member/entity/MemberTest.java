package com.notifit.domain.member.entity;

import com.notifit.global.exception.CustomException;
import com.notifit.global.exception.ErrorCode;
import org.assertj.core.api.AbstractObjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    @DisplayName("회원 엔티티를 생성한다.")
    void createMemberTest() {
        // given
        String username = "username";
        String password = "password";
        String name = "name";
        String phoneNumber = "phoneNumber";

        // when
        Member member = Member.of(username, password, name, phoneNumber);

        // then
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getPassword()).isEqualTo(password);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    @DisplayName("사용자 ID 가 없을 시 회원 엔티티를 생성하면 CustomException 이 발생한다.")
    void emptyUsernameCreateMemberFailTest() {
        // given
        String password = "password";
        String name = "name";
        String phoneNumber = "phoneNumber";

        // when
        AbstractObjectAssert<?, CustomException> extracting = assertThatThrownBy(
                () -> Member.of(null, password, name, phoneNumber))
                .isInstanceOf(CustomException.class)
                .extracting(ex -> (CustomException) ex);

        // then
        extracting.satisfies(ex -> {
            assertThat(ex.getErrorCode()).isEqualTo(ErrorCode.EMPTY_USERNAME);
        });
    }
}