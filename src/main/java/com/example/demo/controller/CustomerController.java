package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys")
public class CustomerController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "customer/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {

        if (params.get("userName") != null && params.get("userName").equals("")) {
            params.put("userName", null);
        }

        if (params.get("password") != null && params.get("password").equals("")) {
            params.put("password", null);
        }

        if (params.get("status") != null && params.get("status").equals("")) {
            params.put("status", null);
        }

        if (params.get("email") != null && params.get("email").equals("")) {
            params.put("email", null);
        }

        if (params.get("phone") != null && params.get("phone").equals("")) {
            params.put("phone", null);
        }

        Query query = new Query(params);
        List<UserEntity> data = userService.queryList(query);
        int total = userService.queryTotal(query);
        Map<String, Object> page = new HashMap();
        page.put("page", query.get("page"));
        page.put("rows", query.get("rows"));
        page.put("total", total);
        PageUtils pageUtils = new PageUtils(data, page);
        return pageUtils;
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public Res save(@RequestBody UserEntity user) {

        if (user.getUserName() == null || user.getUserName().length() < 6) {
            return Res.error("用户名不能为空且不小于6个字符");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return Res.error("密码不能为空且不小于6个字符");
        }

        if (user.getStatus() == null) {
            return Res.error("状态不能为空");
        }

        user.setCreateDate(new Date());
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        userService.save(user);
        return Res.ok();
    }

    @RequestMapping(value = "customer/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null) {
            return Res.error("id获取失败");
        }
        userService.delete(id);
        return Res.ok();
    }

    @RequestMapping(value = "customer", method = RequestMethod.PUT)
    public Res update(@RequestBody UserEntity user) {

        if (user.getId() == null) {
            return Res.error("获取用户id失败");
        }

        if (user.getUserName() != null) {
            user.setUserName(null);
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return Res.error("密码不能为空且长度不低于6位数");
        }

        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        userService.update(user);
        return Res.ok();
    }

}

