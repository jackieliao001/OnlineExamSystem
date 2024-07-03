package com.rabbiter.oes.common.resp;

import com.rabbiter.oes.common.enums.ResponseCode;

public class ApiResultHandler {
    public static <T> ApiResult<T> success(T object) {
        return ApiResult.<T>builder()
                .success(true)
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMsg())
                .data(object)
                .build();
    }

    public static <T> ApiResult<T> success() {
        return success(null);
    }

    public static <T> ApiResult<T> failure() {
        return ApiResult.<T>builder()
                .success(false)
                .code(ResponseCode.FAILURE.getCode())
                .message(ResponseCode.FAILURE.getMsg())
                .build();
    }

    public static <T> ApiResult<T> failure(String message) {
        return ApiResult.<T>builder()
                .success(false)
                .code(ResponseCode.FAILURE.getCode())
                .message(message)
                .build();
    }

    public static <T> ApiResult<T> failure(ResponseCode responseCode) {
        return ApiResult.<T>builder()
                .success(false)
                .code(responseCode.getCode())
                .message(responseCode.getMsg())
                .build();
    }

    public static <T> ApiResult<T> failure(String code, String message) {
        return ApiResult.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

    @Deprecated
    public static <T> ApiResult<T> buildApiResult(String code, String message, T data) {
        return ApiResult.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
