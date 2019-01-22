package com.yxc.dao;

import com.yxc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getUsers();
    int insertUser(User user);
    User getUserByName(@Param(value="userName") String userName);
}
