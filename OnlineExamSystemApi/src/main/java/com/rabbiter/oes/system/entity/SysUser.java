package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.rabbiter.oes.common.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统用户表(SysUser)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:15:54
 */
@Getter
@Setter
public class SysUser implements Serializable {
    private static final long serialVersionUID = 776630034631286344L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 登录账号
     */
    private String accountNum;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 用户名/昵称
     */
    private String userName;
    /**
     * 账户状态:1激活、0停用
     */
    private String accountStatus;
    /**
     * 性别:1男，2女，3未知
     */
    private SexEnum sex;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 邮箱
     */
    private String email;
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

