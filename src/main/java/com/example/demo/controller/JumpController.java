package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由跳转控制层
 */
@Controller
public class JumpController extends BaseController {

    @RequestMapping("/{home}")
    public String Router(@PathVariable("home") String home) {
        return home + ".html";
    }
}
