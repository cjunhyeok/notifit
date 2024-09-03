package com.notifit.controller;

import com.notifit.controller.dtos.Result;
import com.notifit.controller.dtos.member.JoinRequest;
import com.notifit.controller.dtos.member.JoinResponse;
import com.notifit.controller.dtos.member.LoginRequest;
import com.notifit.service.MemberService;
import com.notifit.service.utils.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/member")
    public Result<JoinResponse> join(@RequestBody JoinRequest request) {

        Long savedMemberId = memberService.join(request);

        return new Result<>(new JoinResponse(savedMemberId), "회원가입이 완료됐습니다.");
    }

    @PostMapping("/api/login")
    public Result login(@RequestBody LoginRequest request,
                        HttpServletResponse response) {

        String sessionId = memberService.login(request);

        Cookie sessionCookie = new Cookie(SessionConst.SESSION_ID, sessionId);
        response.addCookie(sessionCookie);

        return new Result(true, "로그인이 완료되었습니다.");
    }
}
