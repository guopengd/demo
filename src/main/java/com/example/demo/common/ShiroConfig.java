package com.example.demo.common;


import com.example.demo.shiro.CustomRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro 配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 验证用户
     * 可以声明多个Realm Bean，Shiro都会把它注入的
     */
    @Bean
    public CustomRealm realm() {
        return new CustomRealm();
    }

    /**
     * 配置shiro的url权限
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        /**访问控制
         chain.addPathDefinition("/view/login", "anon");//可以匿名访问
         chain.addPathDefinition("/changePwd", "authc");//需要登录
         chain.addPathDefinition("/user", "user");//已登录或“记住我”的用户可以访问
         chain.addPathDefinition("/mvnBuild", "authc,perms[mvn:install]");//需要mvn:build权限
         chain.addPathDefinition("/admin", "authc,roles[admin]");//需要js角色
         */
        /**
         使用接口的自定义配置(这里配置所有请求路径都可以匿名访问,需要在对应的接口使用@RequiresPermissions("")进行访问控制)
         因本人iview-admin不太熟悉，所有页面都在同一个index页面进行锚链接，因此不知道如何配置shiro的访问权限
         所以本demo的登录拦截使用的token拦截，在需要权限的方法下使用@RequiresPermissions("")进行注解
         这样即使既可以防止用户未登录操作，也能防止用户登录后直接url访问无访问权限的接口
         */
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    /**
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置shiro的缓存管理
     */
    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

}
