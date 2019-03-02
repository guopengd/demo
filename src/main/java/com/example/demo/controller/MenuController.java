package com.example.demo.controller;

import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "sys")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "menu/list", method = RequestMethod.GET)
    public List<MenuEntity> list() {
        return menuService.queryList(null);
    }


}
