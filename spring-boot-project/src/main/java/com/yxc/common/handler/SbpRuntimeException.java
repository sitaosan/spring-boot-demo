package com.yxc.common.handler;

public class SbpRuntimeException extends RuntimeException {

    private final ErrorCodeAndMsg response;

    public SbpRuntimeException(ErrorCodeAndMsg response) {
        this.response = response;
    }
    public SbpRuntimeException(ErrorCodeAndMsg response, String msg) {
        response.setMsg(msg);
        this.response = response;
    }
    public ErrorCodeAndMsg getResponse() {
        return response;
    }
}
