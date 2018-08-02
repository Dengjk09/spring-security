package com.dengjk.springsecuritydemo.commone.utils;

/**
 * Created by  on 2017/5/3.
 */
public enum ReturnCode {

    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    rediect(401,"登陆失败,重定向到首页"),
    CHECK_FAIL(415,"校验失败"),
    AUTH_FAIL(403, "抱歉，没有权限"),
    NOT_EXIST(404, "不存在"),
    SERVER_ERROR(500, "服务器错误，请稍后重试！"),
    PrintSFTError(408, "暂不能打印SFT！"),
    PrintServiceError(407,"打印服务连接失败");

    private int code;
    private String msg;

     ReturnCode(int code, String msg){
        this.setCode(code);
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
