package com.rabbiter.oes.system.service;

import com.rabbiter.oes.system.vo.LoginUserInfo;

public interface SysLoginService {
    /**
     * 账户登录实现
     * 包含用户、角色、权限数据
     *
     * @param username 用户名
     * @param password 密码
     * @return User Info
     */
    LoginUserInfo login(String username, String password);
}
