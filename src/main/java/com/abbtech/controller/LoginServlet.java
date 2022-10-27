package com.abbtech.controller;

import com.abbtech.domain.User;
import com.abbtech.dto.Session;
import com.abbtech.service.UserService;
import com.abbtech.templateEngine.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    TemplateEngine engine = new TemplateEngine();
    engine.render("login.ftl", new HashMap<>(), resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    boolean status = true;
    if (username.trim().equals("")) {
      status = false;
    }
    if (password.trim().equals("")) {
      status = false;
    }

    if (!status) {
      resp.sendRedirect("/login");
    } else {
      UserService userService = new UserService();
      Optional<User> result = userService.login(username, password);

      if (result.isPresent()) {
        int id = result.get().getId();
        userService.setLastSeen(id);
        Cookie cookie = Session.setCookie(id);
        //
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
      }
      else {
        resp.sendRedirect("/login");
      }
    }
  }

}