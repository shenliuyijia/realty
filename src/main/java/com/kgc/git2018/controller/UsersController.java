package com.kgc.git2018.controller;

import com.kgc.git2018.pojo.Users;
import com.kgc.git2018.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Resource
    UserService userService;

    @RequestMapping("login")
    public String login (String cardid, String password, HttpSession session, Model model){

        Users users = userService.login(cardid,password);
        if (users!=null) {
          session.setAttribute("user",users);
          return "show";
       }
       return "login";
    }
}
