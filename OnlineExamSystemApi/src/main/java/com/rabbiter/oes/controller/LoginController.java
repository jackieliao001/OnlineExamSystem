package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.serviceimpl.LoginServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult<Object> login(@RequestBody Login login, HttpServletResponse response) {

        Integer username = login.getUsername();
        String password = login.getPassword();
        Admin adminRes = loginService.adminLogin(username, password);
        if (adminRes != null) {
            Cookie token = new Cookie("rb_token", adminRes.getCardId());
            token.setPath("/");
            Cookie role = new Cookie("rb_role", "0");
            role.setPath("/");

            //将cookie对象加入response响应
            response.addCookie(token);
            response.addCookie(role);

            return ApiResultHandler.buildApiResult(200, "请求成功", adminRes);
        }

        Teacher teacherRes = loginService.teacherLogin(username,password);
        if (teacherRes != null) {
            Cookie token = new Cookie("rb_token", teacherRes.getCardId());
            token.setPath("/");
            Cookie role = new Cookie("rb_role", "1");
            role.setPath("/");
            response.addCookie(token);
            response.addCookie(role);
            return ApiResultHandler.buildApiResult(200, "请求成功", teacherRes);
        }

        Student studentRes = loginService.studentLogin(username,password);
        if (studentRes != null) {
            Cookie token = new Cookie("rb_token", studentRes.getCardId());
            token.setPath("/");
            Cookie role = new Cookie("rb_role", "2");
            role.setPath("/");
            response.addCookie(token);
            response.addCookie(role);
            return ApiResultHandler.buildApiResult(200, "请求成功", studentRes);
        }

        return ApiResultHandler.buildApiResult(400, "请求失败", null);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie token = new Cookie("rb_token", null);
        token.setPath("/");
        token.setMaxAge(0);
        Cookie role = new Cookie("rb_role", null);
        role.setPath("/");
        role.setMaxAge(0);
        response.addCookie(token);
        response.addCookie(role);
    }
}
