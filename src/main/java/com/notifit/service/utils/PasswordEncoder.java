package com.notifit.service.utils;

public interface PasswordEncoder {

    String encode(String password);
    boolean match(String password, String encodedPassword);
}
