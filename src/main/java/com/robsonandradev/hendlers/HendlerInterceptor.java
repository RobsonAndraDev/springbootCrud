package com.robsonandradev.hendlers;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HendlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
      throws Exception {

      String uri = request.getRequestURI();
      if(uri.endsWith("login") || uri.endsWith("images") || uri.contains("css")){
        return true;
      }

      if(request.getSession().getAttribute("loggedUser") != null) {
        return true;
      }

      response.sendRedirect("login");
      return false;
    }
  }
