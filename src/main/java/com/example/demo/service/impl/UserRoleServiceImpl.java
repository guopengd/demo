package com.example.demo.service.impl;

import com.example.demo.dao.RoleMenuDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.RoleMenuEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户角色管理实现类
 *
 * @author gpd
 * @date 2019/3/29
 */
@Service("RoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleDao roleDao;
    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public UserRoleEntity queryObject(Long id) {
        return roleDao.queryObject(id);
    }

    @Override
    public List<UserRoleEntity> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return roleDao.queryTotal(map);
    }

    @Override
    public void save(UserRoleEntity user) {
        roleDao.save(user);
    }

    @Override
    public void update(UserRoleEntity user) {
        roleDao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        // 查询出当前角色与菜单的所有实体对象
        List<RoleMenuEntity> roleMenus = roleMenuDao.queryList(id);
        ArrayList<Long> roleId = new ArrayList<>();
        // 遍历出角色所拥有的菜单id
        for (RoleMenuEntity roleMenu : roleMenus) {
            roleId.add(roleMenu.getRoleId());
        }
        if (!roleId.isEmpty())
            roleMenuDao.deleteBatch(roleId.toArray());
        roleDao.delete(id);
    }

}
