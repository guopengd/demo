package com.example.demo.handle;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


/**
 * 错误页面拦截器
 * 替代EmbeddedServletContainerCustomizer在外部Tomcat中不起作用的方法
 */
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(401, 403, 404, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (errorCodeList.contains(response.getStatus())) {
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}