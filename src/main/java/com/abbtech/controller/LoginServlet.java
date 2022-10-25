package com.abbtech.controller;

import com.abbtech.domain.User;
import com.abbtech.repository.MessageRepository;
import com.abbtech.repository.UserRepository;
import com.abbtech.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    if (session != null && session.getAttribute("id") != null) {
      resp.sendRedirect("home"); //change
    } else {
      req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp); //changes
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    req.removeAttribute("msg");  //msg
    boolean status = true;
    if (username.trim().equals("")) {
      status = false;
    }
    if (password.trim().equals("")) {
      status = false;
    }

    if (!status) {
      req.setAttribute("page", "login"); //changes
      req.setAttribute("msg", "enter password again");
      req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp); //changes
    } else {
      UserService userService = new UserService();
      Optional<User> result = userService.login(username, password);

      if (result.isPresent()) {
        Optional<User> user = userService.findByUsername(username);
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(1800);
        session.setAttribute("id", user.get().getId());
        resp.sendRedirect("home"); //changes

      }
       else {
        req.setAttribute("page", "login");
        req.setAttribute("msg", "Login Failed.");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
      }


    }
  }
}
