package com.abbtech.controller;

import com.abbtech.templateEngine.TemplateEngine;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class StaticFileServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestPath = !req.getPathInfo().startsWith("/css") ? "/css".concat(req.getPathInfo()) : req.getPathInfo();
        TemplateEngine freeMarker = new TemplateEngine();
        freeMarker.render(requestPath, new HashMap<>(), resp);
    }

}
