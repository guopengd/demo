package com.example.demo.controller;

import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.UserRoleService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys")
public class UserRoleController extends BaseController {

    @Autowired
    UserRoleService roleService;


    @RequestMapping(value = "authorization/list", method = RequestMethod.POST)
    public PageUtils authorization(@RequestBody Map<String, Object> params) {

        if (params.get("roleName") != null && params.get("roleName").equals("")) {
            params.put("roleName", null);
        }

        if (params.get("describe") != null && params.get("describe").equals("")) {
            params.put("describe", null);
        }

        // 获取账号数据
        Query query = new Query(params);
        List<UserRoleEntity> data = roleService.queryList(query);
        // 获取分页数据
        int total = roleService.queryTotal(query);
        Map<String, Object> page = new HashMap();
        page.put("page", query.get("page"));
        page.put("rows", query.get("rows"));
        page.put("total", total);
        return new PageUtils(data, page);
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public Res save(@RequestBody UserRoleEntity role) {

        if (role.getRoleName() == null || role.getRoleName().equals("")) {
            throw new MyException("角色权限名不能为空");
        }

        role.setCreateDate(new Date());
        roleService.save(role);
        return Res.ok("添加成功");
    }


    @RequestMapping(value = "authorization", method = RequestMethod.PUT)
    public Res update(@RequestBody UserRoleEntity role) {

        if (role.getId() == null) {
            throw new MyException("id获取失败");
        }

        if (role.getRoleName() == null || role.getRoleName().equals("")) {
            throw new MyException("角色权限名不能为空");
        }

        roleService.update(role);
        return Res.ok("修改成功");
    }

    @RequestMapping(value = "authorization/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {

        if (id == null) {
            throw new MyException("id获取失败");
        }

        roleService.delete(id);
        return Res.ok("删除成功");
    }

    @RequestMapping(value = "authorization/{id}", method = RequestMethod.GET)
    public UserRoleEntity getObject(@PathVariable Long id) {

        if (id == null) {
            throw new MyException("id获取失败");
        }

        return roleService.queryObject(id);
    }
}
