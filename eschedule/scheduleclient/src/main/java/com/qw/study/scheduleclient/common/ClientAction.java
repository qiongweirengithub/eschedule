package com.qw.study.scheduleclient.common;

/**
 * client action
 * @author qunar-qw
 * @date 18-7-13
 */
public enum ClientAction {

    REGISTER("register", 1)
    ;

    public final String action;

    public final int code;

    ClientAction(String action, int code) {
        this.action = action;
        this.code = code;
    }
}
