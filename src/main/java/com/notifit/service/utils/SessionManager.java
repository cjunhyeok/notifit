package com.notifit.service.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class SessionManager {

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public String createSession(String username) {
        String sessionKey = UUID.randomUUID().toString();
        sessionStore.put(sessionKey, username);

        return sessionKey;
    }

}
