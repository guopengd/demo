package com.example.demo.service.impl;

import com.example.demo.dao.RoleMenuDao;
import com.example.demo.entity.RoleMenuEntity;
import com.example.demo.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色授权菜单实体类
 *
 * @author gpd
 * @date 2019/3/29
 */
@Service("RoleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public List<RoleMenuEntity> queryList(Object roleId) {
        return roleMenuDao.queryList(roleId);
    }

    @Override
    public void saveBatch(List<RoleMenuEntity> list) {
        roleMenuDao.saveBatch(list);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        roleMenuDao.deleteBatch(ids);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenu(Long id, List<Long> ids) {
        // 查询出当前角色与菜单的所有实体对象
        List<RoleMenuEntity> roleMenus = roleMenuDao.queryList(id);
        // temp 表示需要添加的菜单实体; roleId 表示角色所拥有的菜单id
        // save 表示需要批量添加的实体集合; delete 表示需要删除的id集合
        ArrayList<Long> roleId = new ArrayList<>();
        ArrayList<Long> delete = new ArrayList<>();
        List<RoleMenuEntity> save = new ArrayList<>();
        // 遍历出角色所拥有的菜单id
        for (RoleMenuEntity roleMenu : roleMenus) {
            roleId.add(roleMenu.getMenuId());
        }
        // 查询出用户需要删除的权限进行删除
        for (int i = 0; i < roleId.size(); i++) {
            if (!ids.contains(roleId.get(i))) {
                delete.add(roleMenus.get(i).getId());
            }
        }
        if (!delete.isEmpty()) {
            roleMenuDao.deleteBatch(delete.toArray());
        }
        // 查询出角色需要新增的权限进行添加
        for (Long i : ids) {
            if (!roleId.contains(i)) {
                RoleMenuEntity temp = new RoleMenuEntity();
                temp.setRoleId(id);
                temp.setMenuId(i);
                save.add(temp);
            }
        }
        if (!save.isEmpty())
            roleMenuDao.saveBatch(save);
    }
}
