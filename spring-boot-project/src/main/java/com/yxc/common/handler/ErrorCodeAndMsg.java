package com.yxc.common.handler;


public enum ErrorCodeAndMsg {

    DATA_NULL("0001","数据不存在"),
    ADD_ERROR_CODE("0002","数据添加失败"),
    USERNAME_IS_NOT_NULL("0003","用户名不能为空"),
    SYSTEM_ERROR("8888","系统异常"), //系统异常
    NETWORK_ERROR("9999","网络异常");

    ErrorCodeAndMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
