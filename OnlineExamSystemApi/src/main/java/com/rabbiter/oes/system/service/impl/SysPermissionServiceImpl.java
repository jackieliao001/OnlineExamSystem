package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.rabbiter.oes.common.enums.SysInBuildEnum;
import com.rabbiter.oes.common.util.AuthUtils;
import com.rabbiter.oes.system.service.SysMenuService;
import com.rabbiter.oes.system.service.SysPermissionService;
import com.rabbiter.oes.system.service.SysRoleService;
import com.rabbiter.oes.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JackieLiao
 * @description 系统用户权限
 * @package com.rabbiter.oes.system.service.impl
 * @since 2024/5/23
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysUserService userService;
    @Resource
    private SysRoleService roleService;

    @Resource
    private SysMenuService menuService;

    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (AuthUtils.isSupperAdmin(userId)) {
            roles.add(SysInBuildEnum.BUILD_IN_ROLE_CODE.getCode());
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户信息
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (AuthUtils.isSupperAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            List<Long> roleIds = roleService.selectRoleListByUserId(userId);
            if (CollUtil.isEmpty(roleIds)) { // 无角色，用户默认菜单权限
                perms.addAll(menuService.selectMenuPermsByUserId(userId));
            } else {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (Long roleId : roleIds) {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(roleId);
                    perms.addAll(rolePerms);
                }
            }
        }
        return perms;
    }
}
