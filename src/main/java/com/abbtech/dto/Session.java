package com.abbtech.dto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Session {

    private static String COOKIE_NAME = "user_id";

    public static Optional<Cookie> find(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static Cookie findOrThrow(HttpServletRequest req) {
        return find(req)
                .orElseThrow(() -> new RuntimeException("Will never happen due to design"));
    }

    public static Cookie setCookie(int id) {
        return new Cookie(COOKIE_NAME, String.valueOf(id));
    }

    public static int getUserId(HttpServletRequest req) {
        return Integer.parseInt(findOrThrow(req).getValue());
    }

}