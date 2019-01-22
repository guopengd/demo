package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByName(String name);

    List<String> queryAllPerms(Long id);

    List<String> queryAllRoles(Long id);

}
