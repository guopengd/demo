package com.example.demo.test;

import com.example.demo.entity.MenuEntity;
import com.example.demo.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenu() {
        List<MenuEntity> menu = menuService.queryList(null);
        System.out.println(menu);
    }
}
