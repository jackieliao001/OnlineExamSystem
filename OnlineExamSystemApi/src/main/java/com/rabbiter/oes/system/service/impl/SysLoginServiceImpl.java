package com.rabbiter.oes.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.service.SysLoginService;
import com.rabbiter.oes.system.service.SysPermissionService;
import com.rabbiter.oes.system.service.SysUserService;
import com.rabbiter.oes.system.vo.LoginUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SysLoginServiceImpl implements SysLoginService {
    private final SysUserService userService;
    private final SysPermissionService permissionService;

    @Override
    public LoginUserInfo login(String account, String password) {
        // 验证码校验
//        validateCaptcha(username, code, uuid);
        // 登录前置校验
//        loginPreCheck(username, password);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                .eq(SysUser::getPwd, password);
        Optional<SysUser> oneOpt = userService.getOneOpt(queryWrapper);
        if (oneOpt.isPresent()) {
            SysUser sysUser = oneOpt.get();
            LoginUserInfo loginUserInfo = new LoginUserInfo(sysUser, permissionService.getMenuPermission(sysUser.getUserId()));
            // 角色
//            List<SysRoleInfo> userRoleList = roleMenuService.getRoleListByUserId(sysUser.getUserId());
//            user.setUserRoleList(userRoleList);
            return loginUserInfo;
        } else {
            return null;
        }
    }


    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr());
//        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateById(sysUser);
    }
}
