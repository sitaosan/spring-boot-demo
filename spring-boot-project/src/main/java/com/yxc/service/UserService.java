package com.yxc.service;

import com.yxc.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    int addUser(User user);
    User getUserByName(String userName);
}
