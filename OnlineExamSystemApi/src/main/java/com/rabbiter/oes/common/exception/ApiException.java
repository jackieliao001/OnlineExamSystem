package com.rabbiter.oes.common.exception;

import com.rabbiter.oes.common.enums.ErrorCode;

/**
 * API接口请求异常
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.exception
 * @since 2024/6/27
 */
public class ApiException extends RuntimeException {
    private final int errorCode;

    /**
     * 默认错误码信息构造方法
     *
     * @param errorCode 错误码
     */
    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode.getCode();
    }

    /**
     * 自定义错误信息构造方法
     *
     * @param errorCode 错误码
     * @param message   自定义错误信息
     */
    public ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    /**
     * 自定义错误信息构造方法
     *
     * @param errorCode 错误码
     * @param cause     异常堆栈
     */
    public ApiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMsg(), cause);
        this.errorCode = errorCode.getCode();
    }

    /**
     * 自定义错误信息构造方法
     *
     * @param errorCode 错误码
     * @param message   自定义错误信息
     * @param cause     异常堆栈
     */
    public ApiException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
    }

}
