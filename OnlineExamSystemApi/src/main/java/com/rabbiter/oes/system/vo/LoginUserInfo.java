package com.rabbiter.oes.system.vo;

import com.rabbiter.oes.common.enums.UserStatusEnum;
import com.rabbiter.oes.common.enums.UserTypeEnum;
import lombok.Data;

import java.util.Set;

@Data
public class LoginUserInfo {
// 账户基础信息
    /**
     * 主键
     */
    private Long userId;
    /**
     * 登录账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户类型（00系统用户，01普通用户）
     */
    private UserTypeEnum userType;
    /**
     * 账户状态:1激活、0停用
     */
    private UserStatusEnum status;
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;
//权限信息
    /**
     * 权限列表
     */
    private Set<String> permissions;


//本次登录信息
    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    public LoginUserInfo(Long userId, Long deptId, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.permissions = permissions;
    }

}
