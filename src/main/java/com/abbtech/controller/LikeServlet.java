package com.abbtech.controller;

import com.abbtech.domain.User;
import com.abbtech.dto.Session;
import com.abbtech.service.LikeService;
import com.abbtech.templateEngine.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikeServlet extends HttpServlet {

  LikeService likeService = new LikeService();

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Session.getUserId(req);
    TemplateEngine engine = new TemplateEngine();
    Map<String, Object> map = new HashMap<>();
    List<User> users = likeService.getLikedUsers(id);
    map.put("users", users);
    engine.render("people-list.ftl", map, resp);
  }

}