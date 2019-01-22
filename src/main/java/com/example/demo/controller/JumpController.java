package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpController {

    @RequestMapping("/index")
    public String Index() {
        return "index";
    }

    @RequestMapping("/{home}.html")
    public String Router(@PathVariable("home") String home) {
        return home + ".html";
    }
}
