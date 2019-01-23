package com.example.demo.shiro;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utilty.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("==================开始身份验证===================");

        //从主体传过来的认证信息中获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        //从数据库中获取对应用户名的密码
        UserEntity user = userService.queryByName(userName);
        //验证账号是否正确
        logger.info("==================开始账号验证===================");
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        if (user.getStatus().intValue() == 0) {
            throw new LockedAccountException("账号已被冻结");
        }

        logger.info("==================账号验证完成===================");
        return new SimpleAuthenticationInfo(user, password, "CustomRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.info("==================开始权限验证===================");
        //获取权限主体id
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        Long id = user.getId();

        //创建封装用户角色和权限操作符的集合
        List<String> rolesList;
        Set<String> rolesSet = new HashSet<>();
        List<String> permsList;
        Set<String> permsSet = new HashSet<>();

        logger.info("==================开始获取权限操作符===================");
        // 获取redis中的缓存的用户角色和权限操作符
        Set<Object> redisRolesSet = redisUtil.sGet("shiro_roles_" + id);
        String redisPerms = (String) redisUtil.get("shiro_perms_" + id);

        //如果缓存中没有角色集合，则从数据库中获取；如果有，则返回redis中的集合
        if (redisRolesSet == null || redisRolesSet.size() == 0) {
            rolesList = userService.queryAllRoles(id);
            //将role集合存入redis，并设置过期时间
            redisUtil.sSet("shiro_roles_ " + id, rolesList);
            redisUtil.expire("shiro_roles_" + id, 3600 * 6);
            for (String roles : rolesList)
                rolesSet.add(roles);

        } else {
            for (Object roles : redisRolesSet)
                rolesSet.add((String) roles);
        }

        /**
         * redis中没有权限操作符则从数据库中取出放入redis
         * 将所有权限操作符的进行拼接成String放入redis
         * redis中有缓存则将其取出放入set集合
         */
        if (StringUtils.isBlank(redisPerms)) {
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
            ////将perms集合存入redis，并设置过期时间
            redisUtil.set("shiro_perms_" + id, redisPerms.equals("") ? redisPerms : redisPerms.substring(1));
            redisUtil.expire("shiro_perms_" + id, 3600 * 6);
        } else {
            permsSet.addAll(Arrays.asList(redisPerms.trim().split(",")));
        }

        logger.info("==================获取权限操作符成功===================");
        //将set集合放入SimpleAuthorizationInfo中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permsSet);
        info.addRoles(rolesSet);
        return info;
    }
}