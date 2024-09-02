package com.notifit.controller.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Result<T> {

    private T data;
    private String message;

    public Result(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
