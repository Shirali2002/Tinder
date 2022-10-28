package com.abbtech.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

  private boolean isHttp(ServletRequest servletRequest,
                         ServletResponse servletResponse) {
    return servletRequest instanceof HttpServletRequest
        && servletResponse instanceof HttpServletResponse;
  }

  @Override
  default void init(FilterConfig filterConfig) throws ServletException {
  }

  void doHttpFilter(HttpServletRequest req,
                    HttpServletResponse resp,
                    FilterChain filterChain) throws IOException, ServletException;

  @Override
  default void doFilter(ServletRequest servletRequest,
                        ServletResponse servletResponse,
                        FilterChain filterChain) throws IOException, ServletException {
    if (isHttp(servletRequest, servletResponse)) {
      HttpServletRequest req = (HttpServletRequest) servletRequest;
      HttpServletResponse resp = (HttpServletResponse) servletResponse;
      doHttpFilter(req, resp, filterChain);
    } else filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  default void destroy() {
  }

}