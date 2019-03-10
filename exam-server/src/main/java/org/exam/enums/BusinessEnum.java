package org.exam.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.enums
 * @date 2017/10/27 13:38
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum BusinessEnum {
    //
    SERVER_SUCCESS_EXCEPTION(200, "操作成功!"),
    LOGIN_SUCCESS(200, "登录成功!"),


    AUTHORITY_NOT_ENOUGH_EXCEPTION(40001, "权限不足，请联系管理员!"),
    AUTHORITY_NOT_LOGIN_EXCEPTION(40002, "未登录!"),
    USERNAME_PASSWORD_ERROR(40003, "账户名或者密码输入错误!"),
    ACCOUNT_LOCKED_ERROR(40004, "账户被锁定，请联系管理员!"),
    PASSWORD_EXPIRE_ERROR(40005, "密码过期，请联系管理员!"),
    ACCOUNT_EXPIRE_ERROR(40006, "账户过期，请联系管理员!"),
    ACCOUNT_DISABLE_ERROR(40007, "账户被禁用，请联系管理员!"),
    LOGIN_ERROR(40008, "账户被禁用，请联系管理员!"),
    SERVER_ERROR_EXCEPTION(500, "操作失败!"),
    BUSINESS_HANDLE_EXCEPTION(50000, "业务处理异常"),
    ;

    private Integer code ;
    private String message ;

    BusinessEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
