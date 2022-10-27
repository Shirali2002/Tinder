package com.abbtech.controller;

import com.abbtech.domain.Message;
import com.abbtech.dto.Session;
import com.abbtech.service.MessageService;
import com.abbtech.service.UserService;
import com.abbtech.templateEngine.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class MessageServlet extends HttpServlet {
  MessageService messageService = new MessageService();
  UserService userService = new UserService();

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    int id = Session.getUserId(req);
    HashMap<String, Object> map = new HashMap<>();
    int receiverId = Integer.parseInt(req.getPathInfo().substring(1));
    TemplateEngine engine = new TemplateEngine();
    List<Message> messages = messageService.getBySenderAndReceiverId(id, receiverId);
    map.put("messages", messages);
    map.put("fromId", id);
    map.put("toUser", userService.getById(receiverId).orElse(null));
    engine.render("chat.ftl", map, resp);
  }

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    int id = Session.getUserId(req);
    userService.setLastSeen(id);
    int receiverId = Integer.parseInt(req.getPathInfo().substring(1));
    String message = req.getParameter("message");
    if(!message.isEmpty()) messageService.add(new Message(-1, message, LocalDateTime.now(), id, receiverId));
    resp.sendRedirect("/message/" + receiverId);
  }
}