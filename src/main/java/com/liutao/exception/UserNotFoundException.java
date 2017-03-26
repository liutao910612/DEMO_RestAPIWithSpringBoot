package com.liutao.exception;

/**
 * user not found exception
 * Created by liutao on 2017/3/22.
 */
public class UserNotFoundException extends RuntimeException {
    private String name;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
