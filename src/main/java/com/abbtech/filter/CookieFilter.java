package com.abbtech.filter;

import com.abbtech.dto.Session;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements HttpFilter {

    private boolean isCookiePresent(HttpServletRequest req) {
        return Session.find(req).isPresent();
    }

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        if (isCookiePresent(req)) filterChain.doFilter(req, resp);
        else resp.sendRedirect("/login");
    }

}
