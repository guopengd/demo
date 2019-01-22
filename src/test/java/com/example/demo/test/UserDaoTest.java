package com.example.demo.test;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getRoleName() {
        List<String> user = userDao.queryAllRoles(1l);
        System.out.println(user);
    }

    @Test
    public void queryObject() {
        UserEntity user = userDao.queryObject(1l);
        System.out.println(user);
    }

    @Test
    public void queryList() {
        List<UserEntity> user = userDao.queryList(new HashMap<>());
        System.out.println(user);
    }

    @Test
    public void queryTotal() {
        int user = userDao.queryTotal();
        System.out.println(user);
    }
}
