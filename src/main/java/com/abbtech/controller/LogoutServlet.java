package com.abbtech.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("id") == null) {
      resp.sendRedirect("login");
      return;
    }

    session.removeAttribute("id");
    session.getMaxInactiveInterval();

    resp.setHeader("Cache-Control", "no-cache, no-store");
    resp.setHeader("Pragma", "no-cache");
    resp.sendRedirect("login");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
