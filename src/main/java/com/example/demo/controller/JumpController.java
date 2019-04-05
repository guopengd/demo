package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 路由跳转控制层
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@Controller
public class JumpController extends BaseController {

    @RequestMapping("/{home}")
    public String router(@PathVariable("home") String home) {
        return home + ".html";
    }
}
