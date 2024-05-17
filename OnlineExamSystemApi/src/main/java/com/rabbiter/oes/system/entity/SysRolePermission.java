package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统角色权限关联表(SysRolePermission)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
@Getter
@Setter
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 726787185201283519L;
    /**
     * 角色ID
     */
    @TableId
    private Long roleId;
    /**
     * 权限ID
     */
    @TableId
    private Long permissionId;
    /**
     * 是否已删除
     */
    @TableLogic
    private boolean hasDelete;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifiedBy;
    /**
     * 修改时间
     */
    private Date modifiedTime;
}

