package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.system.entity.SysMenu;

import java.util.Set;

/**
 * @author JackieLiao
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @since 2024-05-22 10:23:59
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);
}

