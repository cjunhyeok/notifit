package com.notifit.service.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    @Autowired
    private SessionManager sessionManager;

    @Test
    @DisplayName("회원 ID 를 이용해 세션을 생성한다.")
    void createSessionTest() {
        // given
        String username = "username";

        // when
        String sessionId = sessionManager.createSession(username);

        // then
        Map<String, Object> sessionStore = sessionManager.getSessionStore();
        assertThat(username).isEqualTo(sessionStore.get(sessionId));
    }
}