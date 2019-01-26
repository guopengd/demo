package com.example.demo.service.impl;

import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户角色管理实现类
 */
@Service("RoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleDao RoleDao;

    @Override
    public UserRoleEntity queryObject(Long id) {
        return RoleDao.queryObject(id);
    }

    @Override
    public List<UserRoleEntity> queryList(Map<String, Object> map) {
        return RoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return RoleDao.queryTotal(map);
    }

    @Override
    public void save(UserRoleEntity user) {
        RoleDao.save(user);
    }

    @Override
    public void update(UserRoleEntity user) {
        RoleDao.update(user);
    }

    @Override
    public void delete(Long id) {
        RoleDao.delete(id);
    }

}
