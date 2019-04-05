package com.example.demo.common;


import com.example.demo.shiro.CustomRealm;
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

/**
 * <p>
 * shiro配置类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public CustomRealm realm() {
        return new CustomRealm();
    }

    @Bean
    public EhCacheManager getCacheManager() {
        return new EhCacheManager();
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        logger.info("注入Shiro的Web过滤器-->securityManager", ShiroFilterFactoryBean.class);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        //注入缓存管理器;这个如果执行多次，也是同样的一个对象;详情请看getTokenHandle
        securityManager.setCacheManager(getCacheManager());
        return securityManager;
    }

    /**
     * 下列两个bean用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解无效
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
     * 配置shiro的url访问权限
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        /*
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
        return shiroFilterFactoryBean;
    }

    /**
     * 给shiro的sessionId默认的JSSESSIONID名字改掉
     *
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("webcookie");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(3600 * 24);
        return simpleCookie;
    }
}
