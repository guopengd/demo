package com.example.demo.test;

import com.example.demo.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter6ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void redisTest() {
        // redis存储数据
        String key = "name";
        redisTemplate.opsForValue().set(key, "yukong");
        // 获取数据
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("获取缓存中key为" + key + "的值为：" + value);

        List<UserEntity> userMap = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setUserName("yukong");
        user.setPassword("dfssf");
        user.setId(1L);
        userMap.add(user);
        String userKey = "yukong";
        redisTemplate.opsForValue().set(userKey, userMap);
        List newUser = (List) redisTemplate.opsForValue().get(userKey);
        System.out.println("获取缓存中key为" + userKey + "的值为：" + newUser);
        redisTemplate.delete("yukong");
    }

}
