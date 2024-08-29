package com.notifit.service.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(encodedPassword).isNotEqualTo(password);
    }
}