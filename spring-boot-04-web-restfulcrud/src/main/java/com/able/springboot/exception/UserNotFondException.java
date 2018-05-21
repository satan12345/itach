package com.able.springboot.exception;

public class UserNotFondException extends RuntimeException {

    public UserNotFondException() {
        super("用户不存在");
    }
}
