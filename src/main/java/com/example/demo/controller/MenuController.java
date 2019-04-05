package com.example.demo.controller;

import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单控制层
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@RestController
@RequestMapping(value = "sys")
public class MenuController extends BaseController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "menu/list", method = RequestMethod.POST)
    public PageUtils list(@RequestBody Map<String, Object> params) {

        Query query = new Query(params);
        List<MenuEntity> rows = menuService.queryList(query);
        int total = menuService.queryTotal(query);
        return new PageUtils(rows, total);
    }
}
