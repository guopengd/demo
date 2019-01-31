package com.example.demo.controller;

import com.example.demo.entity.CreateUserEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.token.JwtToken;
import com.example.demo.utilty.MyException;
import com.example.demo.utilty.RedisUtil;
import com.example.demo.utilty.Res;
import com.example.demo.utilty.VerifyUtil;
import net.minidev.json.JSONObject;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class LoginController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "sys/login", method = RequestMethod.POST)
    public Map login(@RequestBody CreateUserEntity user) {
        Map<String, String> resMap = new HashMap<>();
        try {
            // 获取登录相关信息
            String userName = user.getUserName();
            String password = user.getPassword();
            String code = user.getCode();
            String uuid = user.getUuid();
            if (userName == null || password == null || code == null || uuid == null) {
                throw new MyException("未知错误，请联系管理员");
            }
            // 验证验证码，验证成功删除redis缓存
            String validCode = (String) redisUtil.get(uuid);
            if (validCode == null) {
                throw new MyException("验证码已失效");
            }
            if (!code.equalsIgnoreCase(validCode)) {
                throw new MyException("验证码输入错误");
            } else {
                redisUtil.del(uuid);
            }
            // sha256加密
            password = new Sha256Hash(password).toHex();
            //验证账号密码
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(userName, password);
            subject.login(loginToken);
            //生成token返回前端
            HashMap<String, Object> payload = new HashMap<>();
            payload.put("userId", getUserId());
            String token = JwtToken.createToken(payload);
            resMap.put("token", token);
        } catch (UnknownAccountException e) {
            throw new MyException(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            throw new MyException(e.getMessage());
        } catch (LockedAccountException e) {
            throw new MyException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new MyException("账户验证失败");
        }
        return resMap;
    }

    @RequestMapping(value = "sys/logout", method = RequestMethod.POST)
    public Res logOut(HttpServletRequest request) {
        // shiro根据sessionId来存储subject，用户退出浏览器再次访问则subject不存在
        if (ShiroUtils.getUserEntity() != null) {
            redisUtil.del("shiro_perms_" + getUserId(), "shiro_roles_" + getUserId());
            ShiroUtils.logout();
        } else {
            JSONObject data = (JSONObject) request.getAttribute("data");
            Object userId = data.get("userId");
            redisUtil.del("shiro_perms_" + userId, "shiro_roles_" + userId);
        }
        return Res.ok();
    }

    @RequestMapping(value = "sys/createCode/{validUuid}", method = RequestMethod.GET)
    public Map<String, Object> verifyCode(@PathVariable String validUuid) {
        // 如果验证码被刷新，删除刷新前的验证码
        if (validUuid != null && !validUuid.equals("null")) redisUtil.del(validUuid);
        // 生成验证码，第一个参数为验证码，第二个为图片的base64码
        Object[] verify = VerifyUtil.createImage();
        //将验证码存入redis,并设置过期时间
        UUID uuid = UUID.randomUUID();
        redisUtil.set(uuid.toString(), verify[0]);
        redisUtil.expire(uuid.toString(), 60);
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("image", verify[1]);
        return map;
    }

    // 用户重建会话时进行敏感操作需重新login
    @RequestMapping(value = "sys/reLogin", method = RequestMethod.POST)
    public Res reLogin(@RequestBody UserEntity user, HttpServletRequest request) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new MyException("未输入密码");
        }
        JSONObject data = (JSONObject) request.getAttribute("data");
        Long userId = Long.valueOf(data.get("userId").toString());
        UserEntity validUser = userService.queryObject(userId);
        String password = new Sha256Hash(user.getPassword()).toHex();
        // 验证密码
        if (!password.equals(validUser.getPassword())) {
            throw new MyException("密码输入错误");
        }

        // 验证成功后登录
        UsernamePasswordToken loginToken = new UsernamePasswordToken(validUser.getUserName(), password);
        ShiroUtils.getSubject().login(loginToken);
        return Res.ok("验证成功");
    }
}
