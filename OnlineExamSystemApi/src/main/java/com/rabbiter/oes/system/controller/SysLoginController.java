package com.rabbiter.oes.system.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.system.service.impl.SysLoginServiceImpl;
import com.rabbiter.oes.system.vo.LoginBody;
import com.rabbiter.oes.system.vo.RegisterBody;
import com.rabbiter.oes.system.vo.TokenInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author Jackie Liao
 */
@Tag(name = "登录控制器", description = "登录控制接口")
@RestController
public class SysLoginController {

    @Resource
    private SysLoginServiceImpl loginService;

    @Operation(summary = "用户登录", description = "用户登录接口")
    @PostMapping("/login")
    public ApiResult<TokenInfoVO> login(@RequestBody LoginBody login) {
        return loginService.login(login);
    }

    @Operation(summary = "用户退出", description = "退出登录接口")
    @PostMapping("/logout")
    public ApiResult<Object> logout() {
        return loginService.logout();
    }

    @Operation(summary = "用户注册", description = "用户注册或绑定手机号接口")
    @PostMapping("/register")
    public ApiResult<String> register(@Valid @RequestBody RegisterBody userRegisterParam) {
        return loginService.register(userRegisterParam);
    }

    @Operation(summary = "获取验证码", description = "获取验证码接口(key和base64图片)")
    @GetMapping("/captcha")
    public ApiResult<Map<Object, Object>> getCaptcha() {
        return loginService.genCaptcha();
    }
}
