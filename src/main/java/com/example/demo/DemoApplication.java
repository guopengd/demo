package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.example.demo.*")
/**
 * 踩过的最大的坑，一开始粗心使用的com.example.demo.Dao，结果部署到服务器出错，查看错误日志报找不到userDao
 * 各种排查mapper文件和配置文件，各种调试无果，最后仔细一看Dao是大写，遂改成小写，部署成功
 * 问题存疑：为什么内部tomcat能扫描到com.example.demo.dao，部署到服务器的tomcat运行报错？
 */
@MapperScan("com.example.demo.dao")
public class DemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类,用于独立tomcat运行的入口
        return builder.sources(DemoApplication.class);
    }

    //独立Tomcat启动方式
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

