package com.rabbiter.oes.common.resp;

public class ApiResultHandler {
    public static <T> ApiResult<T> success(T object) {
        return new ApiResult<>(object);
    }

    public static ApiResult<Object> success() {
        return success(null);
    }
    public static ApiResult<Object> failure() {
        return new ApiResult<>(500, "请求失败");
    }

    public static ApiResult<String> failure(String message) {
        return new ApiResult<>(500, message);
    }

    public static <T> ApiResult<T> buildApiResult(Integer code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }
}
