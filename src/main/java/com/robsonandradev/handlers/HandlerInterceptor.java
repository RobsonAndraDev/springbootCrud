package com.robsonandradev.handlers;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
      throws Exception {

      String uri = request.getRequestURI();
      System.out.println(uri);
      System.out.println(uri.endsWith("login"));

      if(uri.endsWith("/login") || uri.endsWith("/error") || uri.endsWith("/doLogin")){
        response.sendRedirect("/");
        return true;
      }

      if(request.getSession().getAttribute("loggedUser") != null) {
        return true;
      }

      response.sendRedirect("login");
      return false;
    }
  }
