package com.abbtech.controller;

import com.abbtech.domain.User;
import com.abbtech.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class                                                                                                                                                      UserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserRepository userRepo = new UserRepository();
    List<User> users = new ArrayList<>();
    try{
      users = userRepo.getAll();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    req.setAttribute("users", users);
  }

}