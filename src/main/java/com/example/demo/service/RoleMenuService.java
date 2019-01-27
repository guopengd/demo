package com.example.demo.service;

import com.example.demo.entity.RoleMenuEntity;

import java.util.List;

public interface RoleMenuService {

    // 查询roleId的菜单
    List<RoleMenuEntity> queryList(Object RoleId);

    // 批量增加
    void saveBatch(List<RoleMenuEntity> list);

    // 批量删除
    int deleteBatch(Object[] ids);

    // 授权修改
    void updateRoleMenu(Long id, List<Long> ids);
}
