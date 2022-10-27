package com.abbtech.controller;

import com.abbtech.dto.Session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Cookie cookie = Session.findOrThrow(req);
    cookie.setMaxAge(0);
    resp.addCookie(cookie);
    resp.sendRedirect("/login");
  }

}