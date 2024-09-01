package com.notifit.service.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BcryptPasswordEncoderTest {

    @Test
    @DisplayName("비밀번호를 암호화 한다.")
    void encodeTest() {
        // given
        BcryptPasswordEncoder passwordEncoder = new BcryptPasswordEncoder();
        String password = "password";

        // when
        String encodedPassword = passwordEncoder.encode(password);

        // then
        assertThat(encodedPassword).isNotEqualTo(password);
    }

    @Test
    @DisplayName("암호화된 비밀번호와 일치하는지 확인한다..")
    void matchTest() {
        // given
        BcryptPasswordEncoder passwordEncoder = new BcryptPasswordEncoder();
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);

        // when
        boolean isMatch = passwordEncoder.match(password, encodedPassword);

        // then
        assertThat(isMatch).isTrue();
    }
}