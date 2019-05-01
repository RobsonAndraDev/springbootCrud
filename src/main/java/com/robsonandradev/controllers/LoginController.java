package com.robsonandradev.controllers;
import com.robsonandradev.entities.User;
import com.robsonandradev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class LoginController {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/login")
  public  String login(Model model, HttpSession session) {
    model.addAttribute("user", new User());
    return "login";
  }

  @RequestMapping("/doLogin")
  public String doLogin(User user, HttpSession session) {

    List<User> users = userService.userExists(user.getLogin(), user.getPassword());
    System.out.println(users);
    if ( users == null || users.isEmpty()) {
      return "redirect:/login";
    } else {
      session.setAttribute("loggedUser", user);
      return "redirect:/";
    }
  }
}