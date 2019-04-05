package com.example.demo.service;

import com.example.demo.entity.RoleMenuEntity;

import java.util.List;

/**
 * 角色菜单接口类
 *
 * @author gpd
 * @date 2019/3/29
 */
public interface RoleMenuService {


    /**
     * 查询roleId的菜单
     *
     * @param roleId 用户角色id
     * @return
     */
    List<RoleMenuEntity> queryList(Object roleId);

    /**
     * 批量增加
     *
     * @param list 角色菜单实体集合
     */
    void saveBatch(List<RoleMenuEntity> list);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return
     */
    int deleteBatch(Object[] ids);

    /**
     * 授权修改
     *
     * @param id  父菜单id
     * @param ids 子菜单ids
     */
    void updateRoleMenu(Long id, List<Long> ids);
}
