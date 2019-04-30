package com.robsonandradev.controllers;
import com.robsonandradev.entities.User;
import com.robsonandradev.repositories.dao.UserRepositoryImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

  @RequestMapping("/login")
  public String doLogin(User user, HttpSession session) {

    if ( new UserRepositoryImp().userExists(user.getLogin(), user.getPassword()).isEmpty()) {
      return "login";
    } else {
      session.setAttribute("loggedUser", user);
      return "index";
    }
//    if (new UserRepository().userExists(user)) {
//      session.setAttribute("loggedUser", user);
//      return "index";
//    } else {
//      return "login";
//    }
  }
}