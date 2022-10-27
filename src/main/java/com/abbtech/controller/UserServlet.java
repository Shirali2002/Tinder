package com.abbtech.controller;

import com.abbtech.domain.Like;
import com.abbtech.domain.User;
import com.abbtech.dto.Session;
import com.abbtech.repository.UserRepository;
import com.abbtech.service.LikeService;
import com.abbtech.service.UserService;
import com.abbtech.templateEngine.TemplateEngine;
import com.abbtech.util.CounterUtil;
import freemarker.ext.servlet.FreemarkerServlet;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    LikeService likeService = new LikeService();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int id = Session.getUserId(req);
        List<User> users = userService.getAll();
        if (CounterUtil.getId(id) == users.size()) {
            CounterUtil.removeById(id);
            resp.sendRedirect("/liked");
            return;
        }
        User u = users.get(CounterUtil.getId(id));
        if (u.getId() == id) {
            CounterUtil.mapNext(id);
            u = users.get(CounterUtil.getId(id));
        }
        TemplateEngine engine = new TemplateEngine();
        Map<String, Object> map = new HashMap<>();
        map.put("pp_link", u.getLink());
        map.put("username", u.getUsername());
        CounterUtil.mapNext(id);
        engine.render("like-page.ftl", map, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
        int id = Session.getUserId(req);
        Like like = new Like(-1, id, userService.getAll().get(CounterUtil.getId(id) - 1).getId());
        if (button.equalsIgnoreCase("like") && !likeService.getAll().contains(like)) {
            likeService.add(like);
            resp.sendRedirect("/users");
        } else if (button.equalsIgnoreCase("dislike") && likeService.getAll().contains(like)) {
            likeService.remove(like);
            resp.sendRedirect("/users");
        } else if (button.equalsIgnoreCase("skip")) {
            resp.sendRedirect("/liked");
        }
    }
}