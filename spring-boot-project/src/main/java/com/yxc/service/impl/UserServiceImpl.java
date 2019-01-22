package com.yxc.service.impl;

import com.yxc.common.redis.RedisUtil;
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
    @Autowired
    RedisUtil redis;

    @Override
    public List<User> getUsers() {
        List<User> users = null;
        //先去redis里查找
        users = (List<User>) redis.get("userList");
        if(users==null){
            users =userMapper.getUsers();
            redis.set("userList",users);
        }
        return users;
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
