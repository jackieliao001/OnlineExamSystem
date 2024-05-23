package com.rabbiter.oes.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabbiter.oes.common.domain.BaseEntity;
import com.rabbiter.oes.common.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 系统用户表(SysUser)实体类
 *
 * @author jackie liao
 * @since 2024-05-17 15:15:54
 */
@Getter
@Setter
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 776630034631286344L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码
     */
    @JsonIgnore
    private String pwd;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（00系统用户）
     */
    private String userType;
    /**
     * 账户状态:1激活、0停用
     */
    private String status;
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

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

}

