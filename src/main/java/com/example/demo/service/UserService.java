package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.utilty.Page;

import java.util.List;


public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(int id);

    UserEntity findByName(String name);

    void addUserEntity(UserEntity UserEntity);

    void updateUserEntity(UserEntity UserEntity);

    void deleteUserEntity(UserEntity UserEntity);

    int total();

    List<UserEntity> paging(Page page);

    public List<UserEntity> likeUserEntity(Page page);

    int likeTotal(String name);

}
