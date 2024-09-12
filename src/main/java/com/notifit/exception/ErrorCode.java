package com.notifit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    EMPTY_USERNAME(HttpStatus.BAD_REQUEST, "ID 가 비어있습니다."),
    DUPLICATED_USERNAME(HttpStatus.BAD_REQUEST, "중복된 ID 입니다."),
    USERNAME_NOT_MATCH(HttpStatus.BAD_REQUEST, "ID 가 일치하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    USER_NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    ;

    private final HttpStatus status;
    private final String errorMessage;
}
