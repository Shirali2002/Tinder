package com.abbtech.controller;

import com.abbtech.domain.User;
import com.abbtech.service.UserService;
import com.abbtech.templateEngine.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {

  UserService userService = new UserService();

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    TemplateEngine engine = new TemplateEngine();
    engine.render("register.ftl", new HashMap<>(), resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username").trim();
    String password = req.getParameter("password");
    String pp_link = req.getParameter("pp_link");
    if (!username.isEmpty() && !password.isEmpty()) {
      if (pp_link.isEmpty()){
        pp_link = null;
      }
      userService.register(new User(0, username, password, LocalDateTime.now(), pp_link));
      resp.sendRedirect("/login");
    }
  }

}
