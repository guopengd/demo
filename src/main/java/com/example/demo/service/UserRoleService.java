package com.example.demo.service;

import com.example.demo.entity.UserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户角色管理接口类
 *
 * @author gpd
 * @date 2019/3/29
 */
public interface UserRoleService {

    /**
     * 通过id查询用户角色
     *
     * @param id id
     * @return
     */
    UserRoleEntity queryObject(Long id);

    /**
     * 查询用户角色列表
     *
     * @param map 查询条件
     * @return
     */
    List<UserRoleEntity> queryList(Map<String, Object> map);

    /**
     * 查询用户角色总数
     *
     * @param map 查询条件
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户角色信息
     *
     * @param user 用户实体类
     */
    void save(UserRoleEntity user);

    /**
     * 更新用户角色信息
     *
     * @param user 用户实体类
     */
    void update(UserRoleEntity user);

    /**
     * 删除用户角色信息
     *
     * @param id id
     */
    void delete(Long id);

}
