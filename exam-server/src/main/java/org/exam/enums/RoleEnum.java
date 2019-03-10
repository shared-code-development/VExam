package org.exam.enums;

import lombok.Getter;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.enums
 * @date 09/03/2019 16:13
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum RoleEnum {
    //
    LOGIN("ROLE_login", "登录访问"),
    ADMIN("ROLE_admin", "登录访问")

    ;
    @Getter
    private String code ;
    @Getter
    private String description ;

    RoleEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
