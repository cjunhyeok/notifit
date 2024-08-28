package com.notifit.controller;

import com.notifit.controller.dtos.Result;
import com.notifit.controller.dtos.member.JoinRequest;
import com.notifit.controller.dtos.member.JoinResponse;
import com.notifit.service.MemberService;
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
}
