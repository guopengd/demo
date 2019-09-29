package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import com.example.demo.entity.CreateUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.utilty.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户管理控制层
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@RestController
@RequestMapping(value = "sys")
public class UserController {

    private final UserService userService;

    private final UserRoleService roleService;

    private final RedisUtil redisUtil;

    public UserController(UserService userService, UserRoleService roleService, RedisUtil redisUtil) {
        this.userService = userService;
        this.roleService = roleService;
        this.redisUtil = redisUtil;
    }


    @RequestMapping(value = "customer/list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Map<String, Object> params) {

        Query query = new Query(params);
        // 获取账号数据
        List<UserEntity> data = userService.queryList(query);
        // 获取分页数据
        int total = userService.queryTotal(query);
        // 获取所有用户角色
        List<UserRoleEntity> roles = roleService.queryList(new HashMap<>());
        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("rows", data);
        res.put("roles", roles);
        return res;
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public Res save(@RequestBody CreateUserEntity user) {

        if (user.getUserName() == null || user.getUserName().length() < 6) {
            throw new MyException("用户名不能为空且不小于6个字符");
        }

        if (userService.queryByName(user.getUserName()) != null) {
            throw new MyException("用户名已存在");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new MyException("密码不能为空且不小于6个字符");
        }

        if (user.getCode() == null) {
            throw new MyException("验证码不能为空");
        }

        String validCode = (String) redisUtil.get(user.getUuid());
        if (user.getUuid() == null || validCode == null) {
            throw new MyException("验证码已失效");
        }
        if (user.getUuid().equalsIgnoreCase(validCode)) {
            throw new MyException("验证码错误");
        } else {
            redisUtil.del(validCode);
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
        userService.save(new UserEntity(user));
        return Res.ok("添加成功");
    }

    @SysLog("删除用户")
    @RequiresPermissions("customer:delete")
    @RequestMapping(value = "customer/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null) {
            throw new MyException("id获取失败");
        }
        userService.delete(id);
        return Res.ok("删除成功");
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "customer", method = RequestMethod.PUT)
    public Res update(@RequestBody UserEntity user) {

        if (user.getId() == null) {
            throw new MyException("获取用户id失败");
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

