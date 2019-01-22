package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utilty.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "UserEntityService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao UserEntityDao;

    @Override
    public List<UserEntity> findAll() {
        return UserEntityDao.list();
    }

    @Override
    public UserEntity findById(int id) {
        return UserEntityDao.findById(id);
    }

    @Override
    public void addUserEntity(UserEntity UserEntity) {
        UserEntityDao.addUser(UserEntity);
    }

    @Override
    public void updateUserEntity(UserEntity UserEntity) {
        UserEntityDao.updateUser(UserEntity);
    }

    @Override
    public void deleteUserEntity(UserEntity UserEntity) {
        UserEntityDao.deleteUser(UserEntity);
    }

    @Override
    public List<UserEntity> paging(Page page) {
        return UserEntityDao.list(page);
    }

    @Override
    public int total() {
        return UserEntityDao.total();
    }

    @Override
    public List<UserEntity> likeUserEntity(Page page) {
        return UserEntityDao.likeUser(page);
    }

    @Override
    public int likeTotal(String name) {
        return UserEntityDao.likeTotal(name);
    }

    @Override
    public UserEntity findByName(String name) {

        return UserEntityDao.findByName(name);
    }


}
