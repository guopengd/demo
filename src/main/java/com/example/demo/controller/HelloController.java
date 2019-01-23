package com.example.demo.controller;

import com.example.demo.annotation.SysLog;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.token.JwtToken;
import com.example.demo.utilty.Res;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    //直接网页访问地址http://localhost/hello
    @SysLog("hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RequiresPermissions("sys:hello")
    public Res hello() {
        List<UserEntity> list = userService.queryList(null);
        return Res.ok().put("list", list);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody UserEntity user) {
        Map<String, String> hashMap = new HashMap<>();
        String userName = user.getUserName();
        String password = user.getPassword();
        try {
            //验证账号密码
            Subject subject = ShiroUtils.getSubject();
            // sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(userName, password);
            subject.login(loginToken);

            //获取登录用户主体
            UserEntity shiroUser = (UserEntity) subject.getPrincipal();
            //生成token返回前端
            String token = JwtToken.createToken(new HashMap<>());
            redisTemplate.opsForValue().set("token", token);
            redisTemplate.expire("token", 3600 * 6, TimeUnit.SECONDS);
            hashMap.put("token", token);
        } catch (UnknownAccountException e) {
            return Res.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Res.error(e.getMessage());
        } catch (LockedAccountException e) {
            return Res.error(e.getMessage());
        } catch (AuthenticationException e) {
            return Res.error("账户验证失败");
        } catch (NullPointerException e) {
            return Res.error("请登录");
        }
        return hashMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map logOut() {
        redisTemplate.delete("token");
        redisTemplate.delete("shiro_perms_" + getUserId());
        ShiroUtils.logout();
        return Res.ok();
    }
}
