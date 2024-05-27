package com.rabbiter.oes.common.enums;

import lombok.Getter;

/**
 * 系统模块数据类型枚举
 *
 * @author JackieLiao
 * @since 2024-05-23
 **/
@Getter
public enum SysDataTypeEnum {

    /**
     * 组织
     */
    ORG("ORG"),

    /**
     * 职位
     */
    POSITION("POSITION"),

    /**
     * 资源
     */
    RESOURCE("RESOURCE"),

    /**
     * 角色
     */
    ROLE("ROLE"),

    /**
     * 用户
     */
    USER("USER");

    private final String value;

    SysDataTypeEnum(String value) {
        this.value = value;
    }

}
