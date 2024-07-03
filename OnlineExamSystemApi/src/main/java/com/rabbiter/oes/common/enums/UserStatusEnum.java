package com.rabbiter.oes.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author JackieLiao
 */
@Getter
public enum UserStatusEnum {
    OK("0", "正常"), DISABLE("1", "停用"), LOCK("2", "锁定");

    @EnumValue // 标记数据库存的值是code
    private final String code;
    private final String info;

    UserStatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
