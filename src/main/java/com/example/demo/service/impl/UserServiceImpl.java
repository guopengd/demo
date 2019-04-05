package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 登录用户实现类
 *
 * @author gpd
 * @date 2019/3/29
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public UserEntity queryObject(Long id) {
        return userDao.queryObject(id);
    }

    @Override
    public List<UserEntity> queryList(Map<String, Object> map) {

        return userDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    @Override
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public void update(UserEntity user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<String> queryAllPerms(Long id) {
        return userDao.queryAllPerms(id);
    }

    @Override
    public List<String> queryAllRoles(Long id) {
        return userDao.queryAllRoles(id);
    }

    @Override
    public UserEntity queryByName(String name) {
        return userDao.queryByName(name);
    }
}
