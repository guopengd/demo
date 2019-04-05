package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录用户类
 *
 * @author gpd
 * @date 2019/3/29
 */
public interface UserService {

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return
     */
    UserEntity queryObject(Long id);

    /**
     * 通过name查询用户
     *
     * @param name 用户账号
     * @return
     */
    UserEntity queryByName(String name);

    /**
     * 查询用户列表
     *
     * @param map 用户条件集合
     * @return
     */
    List<UserEntity> queryList(Map<String, Object> map);

    /**
     * 查询用户总数
     *
     * @param map 用户条件集合
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户信息
     *
     * @param user 用户实体类
     */
    void save(UserEntity user);

    /**
     * 更新用户信息
     *
     * @param user 用户实体类
     */
    void update(UserEntity user);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 获取用户所有权限操作符
     *
     * @param id 用户id
     * @return
     */
    List<String> queryAllPerms(Long id);

    /**
     * 获取用户所有角色
     *
     * @param id 用户id
     * @return
     */
    List<String> queryAllRoles(Long id);
}
