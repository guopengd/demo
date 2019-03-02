package com.example.demo.controller;

import com.example.demo.entity.LogEntity;
import com.example.demo.service.LogService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("sys")
@RestController
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "log/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<LogEntity> rows = logService.queryList(query);
        int total = logService.queryTotal(query);
        return new PageUtils(rows, total);
    }

    @RequiresPermissions("log:delete")
    @RequestMapping(value = "log/{id}", method = RequestMethod.DELETE)
    public Res delete(@PathVariable Long id) {
        if (id == null) {
            throw new MyException("获取id失败");
        }

        logService.delete(id);
        return Res.ok("删除成功");
    }

    @RequiresPermissions("log:delete")
    @RequestMapping(value = "log/delete", method = RequestMethod.POST)
    public Res deleteBatch(@RequestBody Long[] ids) {
        if (ids == null || ids.length == 0)
            throw new MyException("获取id失败");

        logService.deleteBatch(ids);
        return Res.ok("批量删除成功");
    }

}
