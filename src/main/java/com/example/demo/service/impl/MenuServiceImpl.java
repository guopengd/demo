package com.example.demo.service.impl;

import com.example.demo.dao.MenuDao;
import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
        return menuDao.queryList(map);
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
