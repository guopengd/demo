package com.example.demo.shiro;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CustomRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("==================开始身份验证===================");

        //从主体传过来的认证信息中获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        //从数据库中获取对应用户名的密码
        UserEntity user = userService.queryByName(userName);
        //验证账号是否正确
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        if (user.getStatus().intValue() == 0) {
            throw new LockedAccountException("账号已被冻结");
        }

        return new SimpleAuthenticationInfo(user, password, "CustomRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.info("==================开始权限验证===================");
        //获取权限主体id
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        Long id = user.getId();

        //创建封装权限操作符的集合
        List<String> permsList;
        Set<String> permsSet = new HashSet<>();

        // 获取redis中的缓存的权限操作符
        String redisPerms = (String) redisTemplate.opsForValue().get("shiro_perms_" + id);

        if (StringUtils.isBlank(redisPerms)) {
            //redis中没有权限操作符则从数据库中取出放入redis
            //将所有权限操作符的进行拼接成String放入redis
            redisPerms = "";
            permsList = userService.queryAllPerms(id);
            for (String perms : permsList) {
                if (StringUtils.isBlank(perms)) {
                    continue;
                }
                String[] PermsArray = perms.trim().split(",");
                for (String daoPerms : PermsArray) {
                    redisPerms += "," + daoPerms.trim();
                    permsSet.add(daoPerms);
                }
            }
            redisTemplate.opsForValue().set("shiro_perms_" + id, redisPerms.equals("") ? redisPerms : redisPerms.substring(1));
            redisTemplate.expire("shiro_perms_" + id, 3600 * 6, TimeUnit.SECONDS);
        } else {
            //redis中有缓存则将其取出放入set集合
            permsSet.addAll(Arrays.asList(redisPerms.trim().split(",")));
        }
        //将set集合放入SimpleAuthorizationInfo中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permsSet);
        return info;
    }
}