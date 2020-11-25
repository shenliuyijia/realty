package com.kgc.git2018.service.impl;

import com.kgc.git2018.mapper.UsersMapper;
import com.kgc.git2018.pojo.Users;
import com.kgc.git2018.pojo.UsersExample;
import com.kgc.git2018.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UsersMapper usersMapper;

    @Override
    public int add(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users select(String cardid) {
        return usersMapper.selectByPrimaryKey(cardid);
    }

    //登录
    @Override
    public Users login(String cardid, String password) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andCardidEqualTo(cardid).andPasswordEqualTo(password);
        List<Users> users = usersMapper.selectByExample(usersExample);
        Users user = users.get(0);
        return user;
    }

}
