package com.kgc.git2018.service;

import com.kgc.git2018.pojo.Users;

public interface UserService {

    //注册
    int add(Users users);

    //身份证
    Users select(String cardid);

    //登录
    Users login(String cardid, String password);


}
