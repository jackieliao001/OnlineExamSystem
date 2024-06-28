package com.rabbiter.oes.common.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的API响应结果对象
 */
@Data
@Builder
public class ApiResult<T>implements Serializable {
    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 错误码，表示一种错误类型
     * 请求成功，状态码为200
     */
    private String code;

    /**
     * 对错误代码的详细解释
     */
    private String message;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private T data;
}
