package com.example.demo.controller;

import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sys")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "menu/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {

        Query query = new Query(params);
        List<MenuEntity> rows = menuService.queryList(query);
        int total = menuService.queryTotal(query);
        return new PageUtils(rows, total);
    }
}
