package com.rabbiter.oes.common.enums;

import lombok.Getter;

/**
 * 系统内置的标识枚举
 *
 * @author JackieLiao
 * @since 2024-05-23
 **/
@Getter
public enum SysInBuildEnum {

    /** 超管用户账号 */
    BUILD_IN_USER_ACCOUNT(1L, "superAdmin", "超管"),

    /** 超管角色编码 */
    BUILD_IN_ROLE_CODE(1L, "superAdmin", "超管"),

    /** 系统内置模块编码 */
    BUILD_IN_MODULE_CODE(1L, "system", "系统内置");

    private final Long value;

    private final String code;

    private final String name;

    SysInBuildEnum(Long value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }
}
