package com.kgc.git2018.service;

import com.kgc.git2018.mapper.UsersMapper;
import com.kgc.git2018.pojo.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public int add(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users select(String cardid) {
        return usersMapper.selectByPrimaryKey(cardid);
    }
}
