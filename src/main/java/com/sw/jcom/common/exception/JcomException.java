package com.sw.jcom.common.exception;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/9
 */
public class JcomException extends Exception {

    private ExceptionEnum exceptionEnum;

    public JcomException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.toString());
        this.exceptionEnum = exceptionEnum;
    }
}
