package com.example.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro验证权限
 *
 * @author gpd
 * @date 2019/3/29
 */
public class validateShiro {

    /**
     * 判断是否拥有权限操作符
     *
     * @param permission 权限操作符
     * @return
     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }

}
