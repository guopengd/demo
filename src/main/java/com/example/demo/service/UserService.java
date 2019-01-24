package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录用户类
 */
public interface UserService {

    // 通过id查询用户
    UserEntity queryObject(Long id);

    // 通过name查询用户
    UserEntity queryByName(String name);

    // 查询用户列表
    List<UserEntity> queryList(Map<String, Object> map);

    // 查询用户总数
    int queryTotal(Map<String, Object> map);

    // 保存用户信息
    void save(UserEntity user);

    // 更新用户信息
    void update(UserEntity user);

    // 删除用户信息
    void delete(Long id);

    // 获取用户所有权限操作符
    List<String> queryAllPerms(Long id);

    // 获取用户所有角色
    List<String> queryAllRoles(Long id);
}
