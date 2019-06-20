package com.example.demo;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 *
 * @author pengdong.guo
 * @date 2019/5/23
 */
class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("hello");
        return proxy.invokeSuper(obj, args);
    }

    public static void main(String[] args) {
        new DemoEntity().getStupid();
    }
}
