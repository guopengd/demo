package com.example.demo.common;

import com.example.demo.handle.TokenHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DefaultViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 踩过坑，如果addInterceptors直接new TokenHandle()，每次拦截获取的都是一个新的TokenHandle类
     * 会导致无法在拦截器中注入bean，因为得到的不是容器已经注入过bean的TokenHandle
     * 所以可以将TokenHandle设置为容器的bean，使其每次获取的为注入过bean的TokenHandle
     */
    @Bean
    public TokenHandle getTokenHandle() {
        return new TokenHandle();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链; addPathPatterns 用于添加拦截规则; excludePathPatterns 用户排除拦截
        registry.addInterceptor(getTokenHandle())
                .addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/", "/index.html", "/login", "/logout", "/view/**", "/error/**", "/image/**"));
    }

    //配置跨域，以便前端使用vue开发
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:81", "http://localhost:8080")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .exposedHeaders("Token")
                .allowCredentials(true).maxAge(3600); //设置是否允许跨域传cookie
    }
}
