package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.shiro.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected UserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getId();
    }

}
