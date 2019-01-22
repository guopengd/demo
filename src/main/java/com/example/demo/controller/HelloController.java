package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.token.JwtToken;
import com.example.demo.utilty.Res;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    //直接网页访问地址http://localhost/hello
    @SysLog("hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RequiresPermissions("sys_hello")
    public Res hello() {
        List<UserEntity> list = userService.queryList(null);
        return Res.ok().put("list", list);
    }

    @SysLog("login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(UserEntity user) {




        Map<String, String> hashMap = new HashMap<>();
        String token = (String) redisTemplate.opsForValue().get("token");
        if (token == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", 500);
            map.put("name", "dsfs");
            token = JwtToken.createToken(map);
            redisTemplate.opsForValue().set("token", token);
            redisTemplate.expire("token", 3600 * 6, TimeUnit.SECONDS);
        }
        hashMap.put("token", token);
        return hashMap;
    }

    @SysLog("logout")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map logOut() {
        Map<String, String> map = new HashMap<>();
        map.put("token", "");
        return map;
    }
}
