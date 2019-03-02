package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import com.example.demo.entity.ElderEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ElderService;
import com.example.demo.service.UserService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import net.minidev.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("sys")
@RestController
public class ElderController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ElderService elderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "elder/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {
        UserEntity user = authorize();
        if (user.getRoleId() != 1) {
            params.put("userId", user.getId());
        }
        Query query = new Query(params);
        List<ElderEntity> rows = elderService.queryList(query);
        int total = elderService.queryTotal(query);
        return new PageUtils(rows, total);
    }

    @SysLog("新增老人信息")
    @RequiresPermissions("customer:update")
    @RequestMapping(value = "elder", method = RequestMethod.POST)
    public Res saveElder(@RequestBody ElderEntity elder) {

        if (elder.getName() == null || elder.getName().equals("")) {
            throw new MyException("老人姓名不能为空");
        }
        if (elder.getAge() == null) {
            throw new MyException("老人年龄不能为空");
        }
        if (elder.getSex() == null) {
            throw new MyException("老人性别不能为空");
        }
        if (elder.getHeight() == null) {
            throw new MyException("老人身高不能为空");
        }
        if (elder.getWeight() == null) {
            throw new MyException("老人体重不能为空");
        }
        if (elder.getHealth() == null) {
            throw new MyException("老人健康状态不能为空");
        }
        elder.setCreateDate(new Date());
        elderService.save(elder);
        return Res.ok("添加老人信息成功");
    }

    @SysLog("修改老人信息")
    @RequiresPermissions("customer:update")
    @RequestMapping(value = "elder", method = RequestMethod.PUT)
    public Res updateElder(@RequestBody ElderEntity elder) {
        if (elder.getId() == null) {
            throw new MyException("获取老人信息失败");
        }
        if (elder.getName() == null || elder.getName().equals("")) {
            throw new MyException("老人姓名不能为空");
        }
        if (elder.getAge() == null) {
            throw new MyException("老人年龄不能为空");
        }
        if (elder.getSex() == null) {
            throw new MyException("老人性别不能为空");
        }
        if (elder.getHeight() == null) {
            throw new MyException("老人身高不能为空");
        }
        if (elder.getWeight() == null) {
            throw new MyException("老人体重不能为空");
        }
        if (elder.getHealth() == null) {
            throw new MyException("老人健康状态不能为空");
        }
        if (elder.getUserName() != null && !elder.getUserName().equals("")) {
            UserEntity user = userService.queryByName(elder.getUserName());
            if (user == null) {
                throw new MyException("绑定失败，请输入正确的账号");
            } else {
                elder.setUserId(user.getId());
            }
        }
        elderService.update(elder);
        return Res.ok("修改老人信息成功");
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "elder/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null)
            throw new MyException("获取id失败");

        elderService.delete(id);
        return Res.ok("删除老人信息成功");
    }

    public UserEntity authorize() {
        JSONObject data = (JSONObject) request.getAttribute("data");
        Long userId = Long.valueOf(data.get("userId").toString());
        UserEntity user = userService.queryObject(userId);
        return user;
    }
}
