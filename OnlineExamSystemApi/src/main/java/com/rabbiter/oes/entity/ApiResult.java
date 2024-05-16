package com.rabbiter.oes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一的API响应结果对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T>implements Serializable {
    /**
     * 错误码，表示一种错误类型
     * 请求成功，状态码为200
     */
    private int code;

    /**
     * 对错误代码的详细解释
     */
    private String message;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private T data;

    /**
     * 成功的默认构造方法
     *
     * @param data 成功时返回的数据
     */
    public ApiResult(T data) {
        this.code = 200; // 或者其他成功的状态码，如 Constant.SUCCESS_CODE
        this.message = "请求成功";
        this.data = data;
    }

    /**
     * 错误的构造方法
     *
     * @param code 错误状态码
     * @param message 错误信息
     */
    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
