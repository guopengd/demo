package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo.annotation.SysLog;
import com.example.demo.entity.LogEntity;
import com.example.demo.service.LogService;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.utilty.AspectCode;
import com.example.demo.utilty.CusAccessObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p>
 * 日志管理AOP
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@Aspect
@Configuration
public class LoginAspect {

    private static Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Autowired
    LogService logService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.annotation.SysLog)")
    public void logPointCut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) {
        try {
            logSave(joinPoint, AspectCode.Before);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     */
    @AfterThrowing("logPointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        try {
            logSave(joinPoint, AspectCode.AfterThrowing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通知方法封装
     *
     * @param joinPoint
     * @param code
     */
    public void logSave(JoinPoint joinPoint, AspectCode code) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogEntity sysLog = new LogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setOperation(syslog.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //判断不同的枚举类型判断执行方法
        if (code == AspectCode.Before) {
            sysLog.setMethod(className + "." + methodName + "()");
        } else {
            sysLog.setMethod(className + "." + methodName + "()" + "执行异常");
        }
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        if (args.length != 0) {
            try {
                String params = JSON.toJSONString(args[0]);
                sysLog.setParams(params);
            } catch (Exception e) {
                logger.error(" \n==============参数出错=====================");
            }
        }
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 设置IP地址
        sysLog.setIp(CusAccessObjectUtil.getIpAddress(request));

        // 用户名
        try {
            String username = ShiroUtils.getUserEntity().getUserName();
            sysLog.setUsername(username);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        sysLog.setCreateDate(new Date());
        // 保存系统日志
        logService.save(sysLog);
        logger.info("\n==============保存日志：" + sysLog + "=====================");
    }
}
