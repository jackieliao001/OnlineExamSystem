package com.rabbiter.oes.common.exception;

import com.rabbiter.oes.common.enums.ResponseCode;
import lombok.Getter;

/**
 * token验证异常类
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.exception
 * @since 2024/6/27
 */
@Getter
public class TokenException extends RuntimeException {
    private final String errorCode;

    /**
     * 默认错误码信息构造方法
     *
     * @param errorCode 错误码
     */
    public TokenException(ResponseCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode.getCode();
    }

    /**
     * 自定义错误信息构造方法
     *
     * @param errorCode 错误码
     * @param message   自定义错误信息
     */
    public TokenException(ResponseCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    /**
     * 自定义错误信息构造方法
     *
     * @param errorCode 错误码
     * @param cause     异常堆栈
     */
    public TokenException(ResponseCode errorCode, Throwable cause) {
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
    public TokenException(ResponseCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
    }
}
