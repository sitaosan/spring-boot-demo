package com.yxc.controller;

import com.alibaba.fastjson.JSON;
import com.yxc.common.dto.UserDto;
import com.yxc.common.handler.ErrorCodeAndMsg;
import com.yxc.common.handler.PageReult;
import com.yxc.common.handler.Result;
import com.yxc.common.handler.SbpRuntimeException;
import com.yxc.common.vo.UserVo;
import com.yxc.pojo.User;
import com.yxc.pojo.UserTask;
import com.yxc.service.UserService;
import com.yxc.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserTaskService userTaskServce;

    @RequestMapping(value = "getUser",method = RequestMethod.POST)
    public Result<PageReult<User>> getUsers(@RequestBody UserVo userVo) throws InterruptedException {
        try{
            log.info("==getUsers==");
            List<User> users = userService.getUsers(userVo);
            if(users!=null){
                Result<PageReult<User>> result = new Result<PageReult<User>>();

                int totalCount = userService.getUserCount(userVo);
                PageReult pageReult = new PageReult(userVo.getPageSize(),userVo.getPageNum(),totalCount,users);
                log.info("==pageReult Json==:{}",pageReult.toJson());
                UserTask userTask = new UserTask();
                userTask.setContent("测试task");
                userTaskServce.addUserTask(userTask);
                return new Result(pageReult);
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
    public Result addUser(@RequestBody UserVo userVo){
        log.info("==addUser==,user:{}", JSON.toJSON(userVo));
        int num = userService.addUser(userVo);
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
