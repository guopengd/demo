package com.example.demo.service.impl;

import com.example.demo.dao.MenuDao;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 菜单实现类
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public MenuEntity queryObject(Long id) {
        return menuDao.queryObject(id);
    }

    @Override
    public List<MenuEntity> queryList(Map<String, Object> map) {
        List<MenuEntity> menus = menuDao.queryList(map);
        List<MenuEntity> root = new ArrayList<>();
        // 获得所有根节点
        for (MenuEntity menu : menus) {
            if (menu.getParentId() != null) {
                if (menu.getParentId().equals(0L)) {
                    root.add(menu);
                }
            }
        }

        // 为一级菜单设置子菜单，通过getChildren递归获取子菜单的子菜单
        for (MenuEntity menu : root) {
            menu.setChildren(getChildren(menu.getId(), menus));
        }

        return root;
    }

    /**
     * 递归查找子菜单
     *
     * @param id    当前菜单id
     * @param menus 要查找的列表
     * @return
     */
    private List<MenuEntity> getChildren(Long id, List<MenuEntity> menus) {
        // 子菜单
        List<MenuEntity> childList = new ArrayList<>();
        for (MenuEntity menu : menus) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId() != null) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuEntity menu : childList) {
            menu.setChildren(getChildren(menu.getId(), menus));
        }
        // 递归退出条件
        if (childList.isEmpty()) {
            return null;
        }
        return childList;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuDao.queryTotal(map);
    }

    @Override
    public void save(MenuEntity menu) {
        menuDao.save(menu);
    }

    @Override
    public void update(MenuEntity menu) {
        menuDao.update(menu);
    }

    @Override
    public void delete(Long id) {
        menuDao.delete(id);
    }
}
