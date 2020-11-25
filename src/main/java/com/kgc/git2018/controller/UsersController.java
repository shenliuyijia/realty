package com.kgc.git2018.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;
import com.kgc.git2018.service.RealService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author shkstart
 * @create 2020-11-25 17:30
 */
@Controller
public class UsersController {
    @Resource
    RealService realService;

    @RequestMapping("show")
    public PageInfo<Real> selectAll(RealExample realExample, Integer pageNum, Integer pageSize) {
        return null;
    }

}
