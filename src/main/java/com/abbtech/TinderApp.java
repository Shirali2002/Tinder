package com.abbtech;

import com.abbtech.controller.LikeServlet;
import com.abbtech.controller.LoginServlet;
import com.abbtech.controller.LogoutServlet;
import com.abbtech.controller.MessageServlet;
import com.abbtech.controller.RegisterServlet;
import com.abbtech.controller.StaticFileServlet;
import com.abbtech.controller.UserServlet;
import com.abbtech.filter.CookieFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {

  private static final EnumSet<DispatcherType> dt = EnumSet.of(DispatcherType.REQUEST);

  public static void main(String[] args) throws Exception {
    Server server = new Server(1212);
    ServletContextHandler servletHandler = new ServletContextHandler();

    servletHandler.addServlet(LoginServlet.class, "/login");
    servletHandler.addServlet(RegisterServlet.class, "/register");
    servletHandler.addServlet(LikeServlet.class, "/liked");
    servletHandler.addServlet(UserServlet.class, "/users");
    servletHandler.addServlet(MessageServlet.class, "/message/*");
    servletHandler.addServlet(LogoutServlet.class, "/logout");
    servletHandler.addServlet(StaticFileServlet.class, "/*");
    servletHandler.addServlet(StaticFileServlet.class, "/message/css/*");

    servletHandler.addFilter(CookieFilter.class, "/users", dt);
    servletHandler.addFilter(CookieFilter.class, "/liked", dt);
    servletHandler.addFilter(CookieFilter.class, "/message/*", dt);
    servletHandler.addFilter(CookieFilter.class, "/logout", dt);

    server.setHandler(servletHandler);
    server.start();
    server.join();
  }

}