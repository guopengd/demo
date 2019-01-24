package com.example.demo.common;


import com.example.demo.shiro.CustomRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 验证用户
     * 可以声明多个Realm Bean，Shiro都会把它注入的
     */
    @Bean
    public CustomRealm realm() {
        return new CustomRealm();
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中
     * 安全管理器：securityManager
     * 可见securityManager是整个shiro的核心
     */
    @Bean
    public EhCacheManager getCacheManager() {
        return new EhCacheManager();
    }

    /**
     * shiro的核心配置，shiro的方法都是通过SecurityManager来实现的
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        logger.info("注入Shiro的Web过滤器-->securityManager", ShiroFilterFactoryBean.class);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        //注入缓存管理器;
        securityManager.setCacheManager(getCacheManager());//这个如果执行多次，也是同样的一个对象;详情请看getTokenHandle
        return securityManager;
    }

    /**
     * 下列两个bean用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入以下两个配置可以解决此问题
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
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
         * 使用接口的自定义配置(这里配置所有请求路径都可以匿名访问,需要在对应的接口使用@RequiresPermissions("")进行访问控制)
         * 因本人iview-admin不太熟悉，所有页面都在同一个index页面进行锚链接，因此不知道如何配置shiro的访问权限
         * 因此本demo前端页面跳转依靠token控制，权限则交给shiro实现
         */
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    /**
     * Shiro的Web过滤器Factory 命名:shiroFilter
     *
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        logger.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //要求登录时的链接(可根据项目的URL进行替换),非必须的属性
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的连接,逻辑也可以自定义，例如返回上次请求的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //用户访问未对其授权的资源时,所显示的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");
        /**
         * 定义shiro过滤链 Map结构
         * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
         * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
         * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        // 因本人设置了在退出时清除redis缓存，所以不用此方法
        // filterChainDefinitionMap.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 给shiro的sessionId默认的JSSESSIONID名字改掉
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("webcookie");
        /**
         * HttpOnly标志的引入是为了防止设置了该标志的cookie被JavaScript读取，
         * 但事实证明设置了这种cookie在某些浏览器中却能被JavaScript覆盖，
         * 可被攻击者利用来发动session fixation攻击
         */
        simpleCookie.setHttpOnly(true);
        /**
         * 设置浏览器cookie过期时间，如果不设置默认为-1，表示关闭浏览器即过期
         * cookie的单位为秒 比如60*60为1小时
         */
        simpleCookie.setMaxAge(3600 * 24);
        return simpleCookie;
    }
}
