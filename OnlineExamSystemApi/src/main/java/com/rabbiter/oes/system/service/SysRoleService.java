package com.rabbiter.oes.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.oes.common.resp.PageRequest;
import com.rabbiter.oes.system.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * 系统角色表(SysRole)表服务接口
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 分页查询
     *
     * @param sysRole     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    IPage<SysRole> queryByPage(SysRole sysRole, PageRequest pageRequest);

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Long> selectRoleListByUserId(Long userId);
}
