package com.rabbiter.oes.common.enums;

import lombok.Getter;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.common.constant
 * @since 2024/6/27
 */
@Getter
public enum ResponseCode {
    /**
     * 方法参数没有校验，内容由输入内容决定
     */
    METHOD_ARGUMENT_NOT_VALID("E400001", "方法参数校验不通过"),

    /**
     * token不存在，验证失败
     * 未认证E401开头
     */
    TOKEN_NOT_FOUND("E401001", "用户未登录"),
    /**
     * token失效，重新登录
     */
    TOKEN_EXPIRED("E401002", "认证已过期，请重新登录"),
    /**
     * token错误，验证失败
     */
    TOKEN_ERROR("E401003", "认证错误，请重新登录"),
    /**
     * 未授权
     */
    UNAUTHORIZED("E401004", "Unauthorized"),

    /**
     * 认证失败
     * 无权限E403开头
     */
    FORBIDDEN("E403001", "Forbidden"),
    /**
     * 成功
     */
    SUCCESS("200", "成功"),
    /**
     * 请求失败
     */
    FAILURE("500", "请求失败"),
    /**
     * 内部错误
     */
    EXCEPTION("500", "服务器开小差啦，快联系管理员。");

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

}
