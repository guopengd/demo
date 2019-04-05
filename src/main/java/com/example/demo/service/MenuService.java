package com.example.demo.service;

import com.example.demo.entity.MenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单接口类
 *
 * @author gpd
 * @date 2019/3/29
 */
public interface MenuService {


    /**
     * 通过id查询菜单
     *
     * @param id id
     * @return
     */
    MenuEntity queryObject(Long id);


    /**
     * 查询菜单列表
     *
     * @param map 查询条件
     * @return
     */
    List<MenuEntity> queryList(Map<String, Object> map);


    /**
     * 查询菜单总数
     *
     * @param map 查询条件
     * @return
     */
    int queryTotal(Map<String, Object> map);


    /**
     * 保存菜单
     *
     * @param user 用户实体类
     */
    void save(MenuEntity user);


    /**
     * 更新菜单
     *
     * @param user 用户实体类
     */
    void update(MenuEntity user);


    /**
     * 删除菜单
     *
     * @param id id
     */
    void delete(Long id);
}
