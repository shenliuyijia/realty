package com.kgc.git2018.controller;
import com.github.pagehelper.PageInfo;
import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;
import com.kgc.git2018.pojo.Users;
import com.kgc.git2018.pojo.UsersExample;
import com.kgc.git2018.service.RealService;
import com.kgc.git2018.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Autowired
    RealService realService;


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

    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        return "redirect:index.jsp";
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

    @RequestMapping("/list")
    public String list(@RequestParam(value = "select",required = false,defaultValue = "1") String select,
                       @RequestParam(value = "name",required = false)String name,
                       @RequestParam(value = "pageNum",required = false)Integer pageNum,
                       Model model){
        System.out.println(select+"aaaaa");
        System.out.println("name:"+name);
        System.out.println(pageNum);
        Integer pageStr=1;
        if (pageNum!=null){
            pageStr=pageNum;
        }
        Integer pageSize = 2;
        RealExample realExample = new RealExample();
        UsersExample usersExample = new UsersExample();
        List<String> s = new ArrayList<>();
        PageInfo<Real> pageInfo = null;
        String newname ="%"+name+"%";
        if (select.equals("2")){
            if (name!=null){
                realExample.createCriteria().andCardidLike(newname);
                System.out.println("ccc-------");
                pageInfo = realService.select(realExample, pageStr, pageSize);
            }else {
                System.out.println("bbb==========");
                pageInfo = realService.select(null, pageStr, pageSize);
            }
        }else {
            if (name!=null){
                usersExample.createCriteria().andNameLike(newname);
                List<Users> users = userService.selectByExample(usersExample);
                if (users!=null){
                    for (Users user : users) {
                        s.add(user.getCardid());
                    }
                }
                realExample.createCriteria().andCardidIn(s);
                pageInfo = realService.select(realExample, pageStr, pageSize);
            }else {
                pageInfo = realService.select(null, pageStr, pageSize);
            }
        }
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("selectid",select);
        model.addAttribute("getName",name);
        return "show";
    }
}
