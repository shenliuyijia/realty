package com.kgc.git2018.controller;
import com.kgc.git2018.pojo.Users;
import com.kgc.git2018.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/toregister")
    public String loo(){
        return "/register";
    }

    //登录
    @RequestMapping("/login")
    public String login (String cardid, String password, HttpSession session, Model model){

        Users users = userService.login(cardid,password);
        if (users!=null) {
            session.setAttribute("user",users);
            return "/show";
        }
        return "/login";
    }

    @RequestMapping("/getCard")
    public String Caid(String cardid, Model model){
        Users users = userService.select(cardid);
        System.out.println(users.toString());
        if(users!=null){
            model.addAttribute("users","o");
        }else {
            model.addAttribute("users","1");
        }
        return "/register";
    }

    @RequestMapping("/register")
    public String register(Users users,Model model){
        String cardid = users.getCardid();
        String s = cardid.substring(17);
        if (s.equals('x')){
            users.setGender(0);
        }else if (Integer.parseInt(s)%2==1){
            users.setGender(1);
        }else if (Integer.parseInt(s)%2==0){
            users.setGender(0);
        }else {
            model.addAttribute("msg","账号输入有误，请重新输入");
            return "register";
        }
        users.setCreatetime(new Date());
        users.setStatus(1);
        int i = userService.add(users);
        if(i>0){
            model.addAttribute("getTrue","注册以成功，现在去登录吗");
        }else {
            model.addAttribute("msg","注册失败");
        }
        return "register";
    }
}
