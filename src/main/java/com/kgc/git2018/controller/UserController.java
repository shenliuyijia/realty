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
    public void Register(@RequestParam("cardid")String cardid, @RequestParam("name") String name, @RequestParam("gender") Integer gender, @RequestParam("createtime") String createtime, @RequestParam("password") String password, @RequestParam("status") Integer status, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         cardid = request.getParameter("cardid");
         name = request.getParameter("name");
         String sex = request.getParameter("gender");
         if(sex!=null){
             gender = Integer.parseInt(sex);
         }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(createtime);
        password = request.getParameter("password");
        String swe = request.getParameter("status");
        if(swe!=null){
            status = Integer.parseInt(swe);
        }
        Users users = new Users();
        users.setCardid(cardid);
        users.setName(name);
        users.setGender(gender);
        users.setCreatetime(date);
        users.setPassword(password);
        users.setStatus(status);
        int i = userService.add(users);
        if(i>0){
           out.write("<script>alert('注册已成功,现在去登录吗！');location.href='/login';</script>");
        }else {
            out.write("<script>alert('注册失败');location.href='/register';</script>");
        }
    }
}
