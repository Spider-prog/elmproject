package com.neusoft.elmboot.intercept;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.neusoft.elmboot.util.TokenUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Value("${token.refreshTime}")
    private Long refreshTime;
    @Value("${token.expiresTime}")
    private Long expiresTime;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            log.info("object instanceof HandlerMethod");
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader("Authorization");
        if (null == token || "".equals(token.trim())) {
            log.info("null");
            return false;
        }
        log.info("==============token:" + token);
        HashMap<String, String> map = tokenUtil.parseToken(token);
        String userId = map.get("userId");
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
        //1.判断 token 是否过期
        if (timeOfUse < refreshTime) {
            log.info("token验证成功");
            return true;
        }
        //超过token刷新时间，刷新 token
        else if (timeOfUse >= refreshTime && timeOfUse < expiresTime) {
            log.info("token待刷新");
            httpServletResponse.setStatus(401);
            return true;
        }
        //token过期就返回 token 无效.
        else {
            log.info("token过期，请重新登录");
            httpServletResponse.setStatus(404);
            return false;
        }
    }
}
