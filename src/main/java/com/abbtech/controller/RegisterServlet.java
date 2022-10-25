package com.abbtech.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username").trim();
    String password = req.getParameter("password").trim();
    String linkId = req.getParameter("linkId").trim();
    super.doPost(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if(session != null && session.getAttribute("id") != null){
      resp.sendRedirect("home");
    }
    else{
      req.setAttribute("page","register");
      req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }

  }
}
