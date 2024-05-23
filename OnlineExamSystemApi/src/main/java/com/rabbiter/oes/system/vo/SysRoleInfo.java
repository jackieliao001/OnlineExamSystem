package com.rabbiter.oes.system.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色信息
 */
@Getter
@Setter
@NoArgsConstructor
public class SysRoleInfo {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 描述
     */
    private String description;


}
