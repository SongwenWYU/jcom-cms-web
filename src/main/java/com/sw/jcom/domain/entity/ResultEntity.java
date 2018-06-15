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
        OK(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),
        PASSWORD_EMPTY(10000, "密码为空"),
        PASSWORD_EQUAL(10000, "与原密码相同"),
        PASSWORD_UPDATE_ERROR(10000, "密码更新失败"),
        ERROR_EMPTY(10101, "填写信息有空值"),
        ERROR_UPDATE(10102, "更新失败");

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
