package com.notifit.controller.dtos.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinResponse {

    private Long memberId;

    public JoinResponse(Long memberId) {
        this.memberId = memberId;
    }
}
