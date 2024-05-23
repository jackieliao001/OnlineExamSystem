package com.rabbiter.oes.system.service;

import java.util.Set;

/**
 * @description 系统用户权限接口
 * @author JackieLiao
 * @package com.rabbiter.oes.system.service
 * @since 2024/5/23
 */
public interface SysPermissionService {
    /**
     * 获取角色权限
     *
     * @param userId 用户信息ID
     * @return 角色权限信息
     */
    Set<String> getRolePermission(Long userId);

    /**
     * 获取角色数据权限
     *
     * @param userId 用户ID
     * @return 角色权限信息
     */
    Set<String> getMenuPermission(Long userId);
}
