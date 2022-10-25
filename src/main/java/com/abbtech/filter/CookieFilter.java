package com.abbtech.filter;

import com.abbtech.dto.Session;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements HttpFilter {

    private boolean isCookiePresent(HttpServletRequest rq) {
        return Session.find(rq).isPresent();
    }

    @Override
    public void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        if (isCookiePresent(rq)) filterChain.doFilter(rq, rs);
        else rs.sendRedirect("/login");
    }

}
