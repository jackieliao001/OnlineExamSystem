package com.rabbiter.oes.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 性别:1男，2女，3未知
 */
@Getter
public enum SexEnum {
    MAN(1, "男"),
    WOMAN(2, "女"),
    UNKNOWN(3, "未知");

    SexEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue // 标记数据库存的值是code
    private final int code;
    private final String descp;
}