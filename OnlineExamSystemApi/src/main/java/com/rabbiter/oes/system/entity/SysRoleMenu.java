package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联表
 * TableName sys_role_menu
 */
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}