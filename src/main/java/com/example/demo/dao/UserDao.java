package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import com.example.demo.utilty.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDao extends BaseDao<UserEntity> {

    List<UserEntity> list();

    UserEntity findById(int i);

    UserEntity findByName(String name);

    void addUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(UserEntity user);

    int total();

    int likeTotal(String name);

    List<UserEntity> list(Page page);

    List<UserEntity> likeUser(Page page);
}
