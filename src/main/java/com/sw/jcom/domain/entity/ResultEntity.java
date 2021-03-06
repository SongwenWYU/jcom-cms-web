package com.sw.jcom.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/15
 */
@Setter
@Getter
public class ResultEntity {

    public enum Code{
        /**
         *
         */
        OK(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),
        PASSWORD_EMPTY(10000, "密码为空"),
        PASSWORD_EQUAL(10001, "与原密码相同"),
        PASSWORD_NEW_NOT_EQUAL(10002, "修改密码两次输入不相同"),
        PASSWORD_NOT_EQUAL(10003, "原密码错误"),
        PASSWORD_UPDATE_ERROR(10004, "密码更新失败"),
        MENU_HAVA_CHILD(10203, "含有子菜单"),
        MENU_URL_EMPTY(10204, "菜单路径为空"),
        ERROR_EMPTY(10101, "填写信息有空值"),
        ERROR_UPDATE(10102, "更新失败"),
        ERROR_ADD(10102, "新增失败"),
        ERROR_DELETE(10103, "删除失败"),
        USER_REG_ERROR(10200, "注册失败"),
        USER_REG_EXIST(10201, "用户名已经存在");

        private Integer code;
        private String message;

        private Code(Integer code, String message){
            this.code = code;
            this.message = message;
        }
    }

    private Integer code = HttpStatus.OK.value();;
    private Object content;

    public ResultEntity(Object content) {
        this.content = content;
    }

    public ResultEntity(int status, Object content) {
        this(content);
        this.code = status;
    }

    public ResultEntity(Code code) {
        this.content = code.message;
        this.code = code.code;
    }
}
