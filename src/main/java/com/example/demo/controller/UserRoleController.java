package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import com.example.demo.entity.IdAndIdsEntity;
import com.example.demo.entity.MenuEntity;
import com.example.demo.entity.RoleMenuEntity;
import com.example.demo.entity.UserRoleEntity;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleMenuService;
import com.example.demo.service.UserRoleService;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.PageUtils;
import com.example.demo.utilty.Query;
import com.example.demo.utilty.Res;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户权限管理控制层
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@RestController
@RequestMapping(value = "sys")
public class UserRoleController extends BaseController {

    private final UserRoleService roleService;

    private final MenuService menuService;

    private final RoleMenuService roleMenuService;

    public UserRoleController(UserRoleService roleService, MenuService menuService, RoleMenuService roleMenuService) {
        this.roleService = roleService;
        this.menuService = menuService;
        this.roleMenuService = roleMenuService;
    }

    @RequestMapping(value = "role/list", method = RequestMethod.POST)
    public PageUtils roleList(@RequestBody Map<String, Object> params) {

        if (params.get("roleName") != null && params.get("roleName").equals("")) {
            params.put("roleName", null);
        }

        if (params.get("describe") != null && params.get("describe").equals("")) {
            params.put("describe", null);
        }

        Query query = new Query(params);
        List<UserRoleEntity> rows = roleService.queryList(query);
        int total = roleService.queryTotal(query);
        return new PageUtils(rows, total);
    }

    @RequestMapping(value = "role", method = RequestMethod.POST)
    public Res saveRole(@RequestBody UserRoleEntity role) {

        if (role.getRoleName() == null || role.getRoleName().equals("")) {
            throw new MyException("角色权限名不能为空");
        }

        if (role.getDescribe() == null || role.getDescribe().equals("")) {
            throw new MyException("角色描述不能为空");
        }

        role.setCreateDate(new Date());
        roleService.save(role);
        return Res.ok("添加成功");
    }


    @RequestMapping(value = "role", method = RequestMethod.PUT)
    public Res updateRole(@RequestBody UserRoleEntity role) {

        if (role.getId() == null) {
            throw new MyException("id获取失败");
        }

        if (role.getRoleName() == null || role.getRoleName().equals("")) {
            throw new MyException("角色权限名不能为空");
        }

        if (role.getDescribe() == null || role.getDescribe().equals("")) {
            throw new MyException("角色描述不能为空");
        }

        roleService.update(role);
        return Res.ok("修改成功");
    }

    @SysLog("删除角色")
    @RequestMapping(value = "role/{id}", method = RequestMethod.DELETE)
    public Res deleteRole(@PathVariable Long id) {

        if (id == null) {
            throw new MyException("id获取失败");
        }

        roleService.delete(id);
        return Res.ok("删除成功");
    }

    @RequestMapping(value = "role/{id}", method = RequestMethod.GET)
    public UserRoleEntity getObject(@PathVariable Long id) {

        if (id == null) {
            throw new MyException("id获取失败");
        }

        return roleService.queryObject(id);
    }

    @RequestMapping(value = "role/menu/{id}", method = RequestMethod.GET)
    public Map<String, Object> roleMenuQuery(@PathVariable Long id) {
        List<RoleMenuEntity> roleMenu = roleMenuService.queryList(id);
        List<MenuEntity> menu = menuService.queryList(null);

        Map<String, Object> data = new HashMap<>();
        data.put("roleMenu", roleMenu);
        data.put("menu", menu);
        return data;
    }

    @SysLog("修改角色菜单")
    @RequestMapping(value = "role/menu", method = RequestMethod.POST)
    public Res authorizationMenu(@RequestBody IdAndIdsEntity params) {

        if (params.getId() == null) {
            throw new MyException("获取角色id失败");
        }

        roleMenuService.updateRoleMenu(params.getId(), params.getIds());
        return Res.ok("修改角色菜单成功");
    }
}