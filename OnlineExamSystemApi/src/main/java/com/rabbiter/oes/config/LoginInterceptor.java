package com.rabbiter.oes.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查
 * 1.配置到拦截器要拦截哪些请求
 * 2.把这些配置放在容器中
 * <p>
 * 实现HandlerInterceptor接口
 * </p>
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     * 登录检查写在这里，如果没有登录，就不执行目标方法
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getCookies() == null) {
            return false;
        }
        for (Cookie cookie : request.getCookies()) {
            if ("rb_token".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}