package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.token.JwtToken;
import com.example.demo.utilty.RedisUtil;
import com.example.demo.utilty.Res;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody UserEntity user) {
        Map<String, String> hashMap = new HashMap<>();
        try {
            String userName = user.getUserName();
            String password = user.getPassword();
            // sha256加密
            password = new Sha256Hash(password).toHex();
            //验证账号密码
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(userName, password);
            subject.login(loginToken);
            //生成token返回前端
            String token = JwtToken.createToken(new HashMap<>());
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
        redisUtil.del("shiro_perms_" + getUserId(), "shiro_roles_" + getUserId());
        ShiroUtils.logout();
        return Res.ok();
    }
}
