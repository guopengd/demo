package com.example.demo.common;

import com.example.demo.handle.ErrorPageInterceptor;
import com.example.demo.handle.TokenHandle;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * <p>
 * 前端页面控制器
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@Configuration
public class DefaultViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/");
            container.addErrorPages(error401Page, error404Page, error405Page, error500Page);
        });
    }

    @Bean
    public TokenHandle getTokenHandle() {
        return new TokenHandle();
    }

    @Bean
    public ErrorPageInterceptor getErrorPageInterceptor() {
        return new ErrorPageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链; addPathPatterns 用于添加拦截规则; excludePathPatterns 用户排除拦截
        registry.addInterceptor(getTokenHandle())
                .addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/", "/index", "/sys/createCode/{validUuid}", "/sys/login", "/sys/customer", "/view/**"));
        registry.addInterceptor(getErrorPageInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList("/", "/index"));
    }

    /**
     * 配置跨域，以便前端使用admin开发
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:81", "http://localhost:8080")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .exposedHeaders("Token")
                .allowCredentials(true).maxAge(3600);
    }
}
