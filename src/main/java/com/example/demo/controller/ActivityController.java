package com.example.demo.controller;

import com.example.demo.entity.ActivityEntity;
import com.example.demo.service.ActivityService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("sys")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "activity/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<ActivityEntity> rows = activityService.queryList(query);
        int total = activityService.queryTotal(query);
        return new PageUtils(rows, total);
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "activity", method = RequestMethod.POST)
    public Res save(@RequestBody ActivityEntity activity) {
        if (activity.getName() == null || activity.getName().equals("")) {
            throw new MyException("活动名称不能为空");
        }
        if (activity.getDescription() == null || activity.getDescription().equals("")) {
            throw new MyException("活动描述不能为空");
        }
        activity.setCreateDate(new Date());
        activityService.save(activity);
        return Res.ok("保存活动成功");
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "activity", method = RequestMethod.PUT)
    public Res update(@RequestBody ActivityEntity activity) {

        if (activity.getName() == null || activity.getName().equals("")) {
            throw new MyException("活动名称不能为空");
        }
        if (activity.getDescription() == null || activity.getDescription().equals("")) {
            throw new MyException("活动描述不能为空");
        }
        activityService.update(activity);
        return Res.ok("修改活动成功");
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "activity/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null) {
            throw new MyException("获取活动失败");
        }
        activityService.delete(id);
        return Res.ok("删除成功");
    }
}
