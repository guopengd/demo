package com.example.demo.service;

import com.example.demo.entity.RoleMenuEntity;
import com.example.demo.entity.UserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户角色管理接口类
 */
public interface UserRoleService {

    // 通过id查询用户角色
    UserRoleEntity queryObject(Long id);

    // 查询用户角色列表
    List<UserRoleEntity> queryList(Map<String, Object> map);

    // 查询用户角色总数
    int queryTotal(Map<String, Object> map);

    // 保存用户角色信息
    void save(UserRoleEntity user);

    // 更新用户角色信息
    void update(UserRoleEntity user);

    // 删除用户角色信息
    void delete(Long id);

}
