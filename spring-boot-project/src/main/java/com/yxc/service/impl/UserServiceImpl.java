package com.yxc.service.impl;

import com.yxc.dao.UserMapper;
import com.yxc.pojo.User;
import com.yxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }
    @Override
    public User getUserByName(String userName){
         return userMapper.getUserByName(userName);
    }
}
