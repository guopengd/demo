package com.example.demo.common;

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

@Configuration
public class DefaultViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 为了响应ivew-admin的history模式，使url如显示为正常url而非hash模式
     * 再此将401,404,500错误页面跳转回index.html，并在admin中设置错误页面
     * 在springboot2.0之前用mbeddedServletContainerCustomizer类来实现该功能。
     * 在Spring Boot2.0以上配置嵌入式Servlet容器时EmbeddedServletContainerCustomizer类不存在
     * 被WebServerFactoryCustomizer替代
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/index");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index");
            ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/index");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/index");
            container.addErrorPages(error401Page, error404Page, error405Page, error500Page);
        });
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
                .excludePathPatterns(Arrays.asList("/", "/index.html", "/sys/login",  "/sys/customer","/view/**"));
    }

    //配置跨域，以便前端使用admin开发
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:81", "http://localhost:8080")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .exposedHeaders("Token")
                .allowCredentials(true).maxAge(3600); //设置是否允许跨域传cookie
    }
}
