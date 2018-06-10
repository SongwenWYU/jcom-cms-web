package com.sw.jcom.common.exception;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/9
 */
public enum  ExceptionEnum {
    /**
     * 用户未登录
     */
    NO_LOGIN(10000, "用户未登录"), NO_USER(10001, "未查询到用户");

    private int code;
    private String msg;

    private ExceptionEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    @Override
    public String toString() {
        return msg;
    }

}
