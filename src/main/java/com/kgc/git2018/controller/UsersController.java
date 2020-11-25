package com.kgc.git2018.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;
import com.kgc.git2018.service.RealService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-11-25 17:30
 */
@Controller
public class UsersController {
    @Resource
    RealService realService;

    //退出
    @RequestMapping("logout.do")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        return "redirect:login.jsp";
    }

}
