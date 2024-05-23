package com.rabbiter.oes.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统用户角色关联表(SysUserRole)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:26
 */
@Getter
@Setter
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -40459431683818723L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}

