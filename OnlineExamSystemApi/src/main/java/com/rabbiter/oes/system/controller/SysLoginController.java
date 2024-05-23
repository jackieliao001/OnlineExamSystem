package com.rabbiter.oes.system.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.system.service.impl.SysLoginServiceImpl;
import com.rabbiter.oes.system.vo.LoginBody;
import com.rabbiter.oes.system.vo.LoginUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制
 *
 * @author Jackie Liao
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/system")
public class SysLoginController {
    private final SysLoginServiceImpl loginService;

    @PostMapping("/login")
    public ApiResult<Object> login(@RequestBody LoginBody login, HttpServletResponse response) {
        String username = login.getUsername();
        String password = login.getPassword();
        LoginUserInfo userRes = loginService.login(username, password);
        if (userRes == null) {
            return ApiResultHandler.buildApiResult(400, "请求失败", "账户密码错误!");
        }

        Cookie token = new Cookie("rb_token", userRes.getToken());
        token.setPath("/");

        Cookie role = new Cookie("rb_role", "1");
        role.setPath("/");

        //将cookie对象加入response响应
        response.addCookie(token);
        response.addCookie(role);

        return ApiResultHandler.buildApiResult(200, "请求成功", userRes);
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
