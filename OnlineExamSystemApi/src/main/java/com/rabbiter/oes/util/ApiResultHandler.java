package com.rabbiter.oes.util;

import com.rabbiter.oes.entity.ApiResult;

public class ApiResultHandler {
    public static ApiResult<Object> success(Object object) {
        return new ApiResult<>(object);
    }

    public static ApiResult<Object> success() {
        return success(null);
    }
    public static ApiResult<Object> failure() {
        return new ApiResult<>(500, "请求失败");
    }

    public static <T> ApiResult<T> buildApiResult(Integer code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }
}
