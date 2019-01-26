package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.RegexUtil;
import com.example.demo.utilty.Res;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    UserRoleService roleService;

    @RequestMapping(value = "customer/list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Map<String, Object> params) {

        if (params.get("userName") != null && params.get("userName").equals("")) {
            params.put("userName", null);
        }

        if (params.get("password") != null && params.get("password").equals("")) {
            params.put("password", null);
        }

        if (params.get("email") != null && params.get("email").equals("")) {
            params.put("email", null);
        }

        if (params.get("phone") != null && params.get("phone").equals("")) {
            params.put("phone", null);
        }

        Query query = new Query(params);
        // 获取账号数据
        List<UserEntity> data = userService.queryList(query);
        // 获取分页数据
        int total = userService.queryTotal(query);
        Map<String, Object> page = new HashMap();
        page.put("page", query.get("page"));
        page.put("rows", query.get("rows"));
        page.put("total", total);
        // 获取所有用户角色
        List<UserRoleEntity> roles = roleService.queryList(new HashMap<>());
        // 将数据返回
        Map<String, Object> res = new HashMap<>();
        res.put("page", page);
        res.put("rows", data);
        res.put("roles", roles);
        return res;
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public Res save(@RequestBody UserEntity user) {

        if (user.getUserName() == null || user.getUserName().length() < 6) {
            throw new MyException("用户名不能为空且不小于6个字符");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new MyException("密码不能为空且不小于6个字符");
        }

        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        if (RegexUtil.isNotNull(user.getEmail()) && !RegexUtil.isEmail(user.getEmail())) {
            throw new MyException("请输入正确的邮箱");
        }

        if (RegexUtil.isNotNull(user.getPhone()) && !RegexUtil.isPhone(user.getPhone())) {
            throw new MyException("请输入正确的电话号码");
        }

        user.setCreateDate(new Date());
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        userService.save(user);
        return Res.ok("添加成功");
    }

    @RequestMapping(value = "customer/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null) {
            throw new MyException("id获取失败");
        }
        userService.delete(id);
        return Res.ok("删除成功");
    }

    @RequestMapping(value = "customer", method = RequestMethod.PUT)
    public Res update(@RequestBody UserEntity user) {

        if (user.getId() == null) {
            throw new MyException("获取用户id失败");
        }

        if (user.getUserName() != null) {
            user.setUserName(null);
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new MyException("密码不能为空且长度不低于6位数");
        }

        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        if (!RegexUtil.isNull(user.getEmail()) && !RegexUtil.isEmail(user.getEmail())) {
            throw new MyException("请输入正确的邮箱");
        }

        if (!RegexUtil.isNull(user.getPhone()) && !RegexUtil.isPhone(user.getPhone())) {
            throw new MyException("请输入正确的电话号码");
        }

        // 防止未修改的密码再次被加密
        if (StringUtils.equals(userService.queryObject(user.getId()).getPassword(), user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }

        userService.update(user);
        return Res.ok("修改成功");
    }

    @RequestMapping(value = "customer/{id}", method = RequestMethod.GET)
    public UserEntity authorization(@PathVariable Long id) {

        if (id == null) {
            throw new MyException("获取用户id失败");
        }

        return userService.queryObject(id);
    }
}

