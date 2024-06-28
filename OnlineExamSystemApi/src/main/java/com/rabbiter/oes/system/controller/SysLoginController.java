package com.rabbiter.oes.system.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.core.jwt.JwtPayloadInfo;
import com.rabbiter.oes.system.service.JWTService;
import com.rabbiter.oes.system.service.impl.SysLoginServiceImpl;
import com.rabbiter.oes.system.vo.LoginBody;
import com.rabbiter.oes.system.vo.LoginUserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制
 *
 * @author Jackie Liao
 */
@RestController
@RequestMapping(value = "/system")
public class SysLoginController {
    @Resource
    private SysLoginServiceImpl loginService;
    @Resource
    private JWTService jwtService;

    @PostMapping("/login")
    public ApiResult<Object> login(@RequestBody LoginBody login) {
        String username = login.getAccount();
        String password = login.getPassword();
        LoginUserInfo userRes = loginService.login(username, password);
        if (userRes == null) {
            return ApiResultHandler.buildApiResult(400, "请求失败", "账户密码错误!");
        }
        JwtPayloadInfo build = JwtPayloadInfo.builder().username(userRes.getNickName()).build();
        String token = jwtService.sign(build);

        return ApiResultHandler.buildApiResult(200, "登录成功", token);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        // 清除token
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
/*    @GetMapping("/getInfo")
    public ApiResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        ApiResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }*/

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
/*    @GetMapping("/getRouters")
    public ApiResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return ApiResult.success(menuService.buildMenus(menus));
    }*/
}
