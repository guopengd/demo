package com.example.demo.service;

import com.example.demo.entity.MenuEntity;

import java.util.List;
import java.util.Map;

public interface MenuService {

    // 通过id查询菜单
    MenuEntity queryObject(Long id);

    // 查询菜单列表
    List<MenuEntity> queryList(Map<String, Object> map);

    // 查询菜单总数
    int queryTotal(Map<String, Object> map);

    // 保存菜单
    void save(MenuEntity user);

    // 更新菜单
    void update(MenuEntity user);

    // 删除菜单
    void delete(Long id);
}
