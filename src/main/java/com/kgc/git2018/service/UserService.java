package com.kgc.git2018.service;

import com.kgc.git2018.pojo.Users;

public interface UserService {
    //登录
    Users login(String cardid, String password);

}
