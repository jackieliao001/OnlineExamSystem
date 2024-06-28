package com.rabbiter.oes.common.enums;

import lombok.Getter;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.common.constant
 * @since 2024/6/27
 */
@Getter
public enum ErrorCode {
    /**
     * token不存在，验证失败
     */
    TOKEN_NOT_FOUND(401, "用户未登录"),
    /**
     * token失效，重新登录
     */
    TOKEN_EXPIRED(401, "认证已过期，请重新登录"),
    /**
     * token错误，验证失败
     */
    TOKEN_ERROR(403, "认证错误，请重新登录"),
    /**
     * 内部错误
     */
    INTERNAL_SERVER_ERROR(500, "内部错误，请联系管理员。");


    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;

}
