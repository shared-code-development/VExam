package org.exam.common;

import org.exam.bean.entity.TUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前用户对象
 * @author heshiyuan
 */
public class UserUtils {
    /**
     *
     * @return
     */
    public static TUser getCurrentUser() {
        return (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
