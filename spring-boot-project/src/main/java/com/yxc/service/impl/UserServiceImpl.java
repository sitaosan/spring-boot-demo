package com.yxc.service.impl;

import com.yxc.common.dto.UserDto;
import com.yxc.common.redis.RedisUtil;
import com.yxc.common.vo.UserVo;
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
    public List<User> getUsers(UserVo userVo) {
        List<User> users = null;
//        //先去redis里查找
//        users = (List<User>) redis.get("userList");
//        if(users==null){
//            users =userMapper.getUsers();
//            redis.set("userList",users);
//        }
        UserDto userDto =  voToDto(userVo);
        users =userMapper.getUsers(userDto);
        return users;
    }

    @Override
    public int addUser(UserVo userVo) {
        User user = voToPojo(userVo);
        return userMapper.insertUser(user);
    }
    @Override
    public User getUserByName(String userName){

         return userMapper.getUserByName(userName);
    }
    @Override
    public int getUserCount(UserVo userVo){
          UserDto userDto =  voToDto(userVo);
          return userMapper.getUserCount(userDto);
    }
    private UserDto voToDto(UserVo userVo){
        UserDto userDto = new UserDto();
        userDto.setUserId(userVo.getUserId());
        userDto.setUserAddress(userVo.getUserAddress());
        userDto.setUserName(userVo.getUserName());
        userDto.setPageNum(userVo.getPageNum());
        userDto.setPageSize(userVo.getPageSize());
        userDto.setStartIdx((userVo.getPageNum()-1)*userVo.getPageSize());
        return userDto;
    }
    private User voToPojo(UserVo userVo){
        User user  = new User();
        user.setUserAddress(userVo.getUserAddress());
        user.setUserName(userVo.getUserName());
        return user;
    }
}
