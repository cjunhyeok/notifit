package com.notifit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifit.controller.dtos.member.JoinRequest;
import com.notifit.controller.dtos.member.LoginRequest;
import com.notifit.service.utils.SessionConst;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 시 요청 입력값을 검증한다.")
    void joinTest() throws Exception {
        // given
        JoinRequest request = JoinRequest
                .builder()
                .username("username")
                .password("password")
                .name("name")
                .phoneNumber("010-1234-5678")
                .isTermOfUseCheck(true)
                .isPrivacyCheck(true)
                .build();

        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("로그인 시 쿠키로 SessionId 가 발급된다.")
    void loginTest() throws Exception {
        // given
        String username = "username";
        JoinRequest joinRequest = JoinRequest
                .builder()
                .username(username)
                .password("password")
                .name("name")
                .phoneNumber("010-1234-5678")
                .isTermOfUseCheck(true)
                .isPrivacyCheck(true)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .content(objectMapper.writeValueAsString(joinRequest))
                .contentType(MediaType.APPLICATION_JSON));

        LoginRequest request = LoginRequest
                .builder()
                .username(username)
                .password("password")
                .build();

        // when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MockHttpSession session = (MockHttpSession) perform.andReturn().getRequest().getSession();
        assertThat(session).isNotNull();  // 세션이 null이 아닌지 확인
        assertThat(session.getAttribute(SessionConst.SESSION_ID)).isEqualTo(username);  // 세션에 저장된 값 검증
    }
}