package com.rabbiter.oes.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author JackieLiao
 */
@Getter
public enum UserTypeEnum {
    /**
     * 系统内置初始化用户，超管、推广
     */
    SYSTEM("00", "系统内置"),
    /**
     * 注册用户
     */
    REGISTER("01", "普通用户"),
    /**
     * 第三方用户
     */
    THIRD("02", "第三方用户");

    @EnumValue // 标记数据库存的值是code
    private final String code;
    private final String info;

    UserTypeEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
