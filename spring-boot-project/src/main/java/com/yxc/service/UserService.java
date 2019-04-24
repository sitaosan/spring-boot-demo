package com.yxc.service;

import com.yxc.common.vo.UserVo;
import com.yxc.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(UserVo userVo);
    int addUser(UserVo userVo);
    User getUserByName(String userName);
    int getUserCount(UserVo userVo);
}
