package com.yxc.controller;

import com.alibaba.fastjson.JSON;
import com.yxc.common.ErrorCodeAndMsg;
import com.yxc.common.Result;
import com.yxc.common.SbpRuntimeException;
import com.yxc.pojo.User;
import com.yxc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public Result getUsers(){
        try{
            log.info("==getUsers==");
            List<User> users = userService.getUsers();
            if(users!=null){
                return new Result(users);
            }else{
                throw new SbpRuntimeException(ErrorCodeAndMsg.DATA_NULL);
            }
        }catch (Exception ex){
            if(ex instanceof SbpRuntimeException){
                throw ex;
            }else{
                log.error("getUsers():{}",ex);
                throw new SbpRuntimeException(ErrorCodeAndMsg.SYSTEM_ERROR,ex.getMessage());
            }
        }
    }

    @RequestMapping(value="addUuser",method = RequestMethod.POST)
    public Result addUser(@RequestBody User user){
        log.info("==addUser==,user:{}", JSON.toJSON(user));
        int num = userService.addUser(user);
        if(num!=1){
            throw new SbpRuntimeException(ErrorCodeAndMsg.ADD_ERROR_CODE);
        }else{
            return new Result(num);
        }
    }
    /**
     * request请求注解说明：
     * @RequestBody 这个是json请求可以是个对象比如User 或者map:Map<String, String> user
     * @RequestParam 这个是表单提交，postman里面用form-data来请求
     * */
    @RequestMapping(value="getUserBbyName")
    public Result getUserBbyName(@RequestParam(value = "userName") String userName){
        try{
            log.info("==getUserBbyName==,userName:{}", userName);
            if("".equals(userName)||userName==null){
                throw  new SbpRuntimeException(ErrorCodeAndMsg.USERNAME_IS_NOT_NULL);
            }else{
                User user = userService.getUserByName(userName);
                if(user ==null){
                    throw  new SbpRuntimeException(ErrorCodeAndMsg.DATA_NULL);
                }else{
                    return new Result(user);
                }
            }
        }catch (Exception ex){
            if(ex instanceof SbpRuntimeException){
                throw ex;
            }else{
                log.error("getUserBbyName():{}",ex);
                throw new SbpRuntimeException(ErrorCodeAndMsg.SYSTEM_ERROR,ex.getMessage());
            }
        }
    }
}
