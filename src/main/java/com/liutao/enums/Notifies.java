package com.liutao.enums;

/**
 * Created by liutao on 2017/3/22.
 */
public enum Notifies {
    SUCCESS_FIRST("subscribe success"),
    FAILED_SUBSCRIBE("subscribe failed."),
    SUCCESS_RENEW("renew success."),
    INSUFFICIENT_BALANCE("balance not enough"),
    SERVICE_TERMINATE("service expire");

    private String message;

    Notifies(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
