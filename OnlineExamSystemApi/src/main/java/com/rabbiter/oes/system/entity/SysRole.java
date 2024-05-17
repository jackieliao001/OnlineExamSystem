package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统角色表(SysRole)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
@Getter
@Setter
public class SysRole implements Serializable {
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
     * 描述
     */
    private String description;
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

