package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
    @TableId
    private Long userId;
    /**
     * 角色ID
     */
    @TableId
    private Long roleId;
    /**
     * 是否已删除
     */
    @TableLogic
    private Boolean hasDelete;
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

