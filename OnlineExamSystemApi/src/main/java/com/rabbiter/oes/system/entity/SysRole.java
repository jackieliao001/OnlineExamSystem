package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rabbiter.oes.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 系统角色表(SysRole)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
@Getter
@Setter
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = -66574279568537854L;
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色权限
     */
    private String roleKey;

    /** 角色排序 */
    private Integer sortNum;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限） */
    private String dataScope;

    /** 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示） */
    private boolean menuCheckStrictly;

    /** 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ） */
    private boolean deptCheckStrictly;

    /** 角色状态（0正常 1停用） */
    private String status;

    /**
     * 描述
     */
    private String remark;

    /** 菜单组 */
    @TableField(exist = false)
    private Long[] menuIds;

    /** 部门组（数据权限） */
    @TableField(exist = false)
    private Long[] deptIds;

    /** 角色菜单权限 */
    @TableField(exist = false)
    private Set<String> permissions;
}

