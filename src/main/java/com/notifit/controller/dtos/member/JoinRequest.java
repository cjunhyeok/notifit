package com.notifit.controller.dtos.member;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String name;
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
            message = "전화번호 형식이 맞지 않습니다.")
    private String phoneNumber;

    @AssertTrue
    private boolean isTermOfUseCheck;
    @AssertTrue
    private boolean isPrivacyCheck;

    @Builder
    public JoinRequest(String username, String password, String name, String phoneNumber, boolean isTermOfUseCheck, boolean isPrivacyCheck) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isTermOfUseCheck = isTermOfUseCheck;
        this.isPrivacyCheck = isPrivacyCheck;
    }
}
