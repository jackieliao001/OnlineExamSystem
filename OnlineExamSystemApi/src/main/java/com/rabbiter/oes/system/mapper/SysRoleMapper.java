package com.rabbiter.oes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.oes.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色表(SysRole)表数据库访问层
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询指定行数据
     *
     * @param page    分页对象
     * @param sysRole 查询条件
     * @return 对象列表
     */
    IPage<SysRole> queryAllByLimit(IPage<SysRole> page, SysRole sysRole);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolePermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Long> selectRoleListByUserId(Long userId);
}

