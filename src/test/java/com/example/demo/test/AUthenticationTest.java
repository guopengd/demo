package com.example.demo.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AUthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    private static Logger logger = LoggerFactory.getLogger(AUthenticationTest.class);

    @Before
    public  void addUser(){
        simpleAccountRealm.addAccount("mark","123456","admin");
    }

    @Test
    public void testAuthentication() {
        //1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark", "123456");
        subject.login(token);
        subject.checkRole("admin");
        System.out.println(subject.isAuthenticated());
    }
}
