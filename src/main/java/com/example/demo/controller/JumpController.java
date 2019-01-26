package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utilty.Res;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JumpController extends BaseController {

    @Autowired
    UserService userService;

    //直接网页访问地址http://localhost/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RequiresPermissions("sys:hello")
    @ResponseBody
    public Res hello() {
        UserEntity list = userService.queryObject(1l);
        return Res.ok().put("list", list);
    }

    @RequestMapping("/{home}")
    public String Router(@PathVariable("home") String home) {
        return home + ".html";
    }
}
