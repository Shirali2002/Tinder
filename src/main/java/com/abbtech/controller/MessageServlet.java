package com.abbtech.controller;

import com.abbtech.domain.Message;
import com.abbtech.repository.MessageRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MessageRepository messageRepo = new MessageRepository();
    List<Message> messages = new ArrayList<>();
    try {
      messages = messageRepo.getAll();
    }
    catch (Exception e){
      e.printStackTrace();
  }
    req.setAttribute("messages",messages);
  }
}
