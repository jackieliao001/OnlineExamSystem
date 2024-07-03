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
    METHOD_ARGUMENT_NOT_VALID("P400001", "方法参数校验不通过"),

    /**
     * token不存在，验证失败
     * Token异常T401开头
     */
    TOKEN_NOT_FOUND("T401001", "用户未登录"),
    /**
     * token失效，重新登录
     */
    TOKEN_EXPIRED("T401002", "认证已过期，请重新登录"),
    /**
     * token错误，验证失败
     */
    TOKEN_ERROR("T401003", "认证错误，请重新登录"),


    /**
     * 未授权A401开头
     */
    UNAUTHORIZED("A401001", "认证失败请重新登录"),
    /**
     * 用户已禁用
     */
    ACCOUNT_DISABLED("A401002", "用户已禁用，请联系客服"),
    /**
     * 账户密码错误
     */
    ACCOUNT_PASSWORD_ERROR("A401003", "用户名或密码错误"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR("A401004", "验证码错误"),
    /**
     * 验证码失效
     */
    CAPTCHA_EXPIRED("A401004", "验证码已过期，请刷新"),


    /**
     * 无访问权限R403开头
     */
    FORBIDDEN("R403001", "权限不足"),


    /**
     * 成功
     */
    SUCCESS("200", "成功"),
    /**
     * 请求失败
     */
    FAILURE("500", "请求失败"),

    /**
     * 业务异常，内部错误,B500开头
     */
    EXCEPTION("B500001", "内部错误，快联系管理员。"),

    /**
     * json转化异常
     */
    JSON_PROCESSING_EXCEPTION("B500002", "JsonProcessingException"),
    /**
     * 业务异常，内部错误
     */
    CAPTCHA_GEN_EXCEPTION("B500003", "验证图片生成失败"),

    NOT_FOUND_KEY("E500001", "未查询到数据"),
    GROUP_QUESTION_FAIL("E501001", "组卷失败");


    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

}
