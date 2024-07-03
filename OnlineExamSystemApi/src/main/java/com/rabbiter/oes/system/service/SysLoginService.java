package com.rabbiter.oes.system.service;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.system.vo.LoginBody;
import com.rabbiter.oes.system.vo.RegisterBody;
import com.rabbiter.oes.system.vo.TokenInfoVO;

import java.io.IOException;
import java.util.Map;

public interface SysLoginService {
    /**
     * 账户登录实现
     * 包含用户、角色、权限数据
     *
     * @param login username 用户名
     *              password 密码
     * @return User Info
     */
    ApiResult<TokenInfoVO> login(LoginBody login);

    ApiResult<String> register(RegisterBody userRegisterParam);

    ApiResult<Object> logout();

    ApiResult<Map<Object, Object>> genCaptcha() throws IOException;
}
