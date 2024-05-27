package com.rabbiter.oes.common.enums;

import lombok.Getter;

/**
 * 用户状态
 *
 * @author JackieLiao
 */
@Getter
public enum UserStatusEnum {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
