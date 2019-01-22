package com.yxc.common;

import lombok.Data;

import java.io.Serializable;

//统一返回结果
@Data
public class Result<T> implements Serializable {
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回描述
    private String msg;

    //请求成功返回码为：0000
    private static final String successCode = "0000";
    public Result(){
        this.code = successCode;
        this.msg = "请求成功";
    }

    public Result(String code,String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public Result(String code,String msg,T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(T data){
        this();
        this.data = data;
    }
}
