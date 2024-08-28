package com.notifit.controller.dtos;

public class Result<T> {

    private T data;
    private String message;

    public Result(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
