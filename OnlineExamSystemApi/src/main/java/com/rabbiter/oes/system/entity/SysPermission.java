package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统权限表(SysPermission)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:20:25
 */
@Getter
@Setter
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 923507062537001805L;
    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long permissionId;
    /**
     * 权限名
     */
    private String permissionName;
    /**
     * 权限资源
     */
    private String resource;
    /**
     * 操作类型
     */
    private String action;
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

