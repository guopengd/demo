package com.example.demo.handle;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.shiro.ShiroUtils;
import com.example.demo.token.JwtToken;
import com.example.demo.token.TokenState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class TokenHandle implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取Token，如果不存在则从header中获取
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("token")) token = cookie.getValue();

        if (token == null) token = request.getHeader("Token");

        // token验证
        Map<String, Object> resultMap = JwtToken.validToken(token);
        TokenState state = TokenState.getTokenState((String) resultMap.get("state"));

        switch (state) {
            case VALID:
                //取出payload中数据,放入到request作用域中
                request.setAttribute("data", resultMap.get("data"));
                //放行
                return true;
            case EXPIRED:
            case INVALID:
                //token过期或者无效，则输出错误信息返回给ajax
                JSONObject outputMSg = new JSONObject();
                outputMSg.put("code", 401);
                outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
                //设置状态码
                //response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                output(outputMSg.toJSONString(), response);
                return false;
        }
        return false;
    }

    public void output(String jsonStr, HttpServletResponse response) throws IOException {
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
        out.flush();
        out.close();

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
