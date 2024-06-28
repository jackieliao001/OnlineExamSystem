package com.rabbiter.oes.core.config;

import com.rabbiter.oes.common.exception.TokenException;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * 全局异常处理类
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.core.config
 * @since 2024/6/27
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 参数校验异常
     *
     * @param e 参数异常
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("handler MethodArgumentNotValidException.参数异常！ msg : -> ", e);
        return ApiResultHandler.failure(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult<String> tokenExceptionHandler(TokenException e) {
        log.error("handler TokenException.", e);
        return new ApiResult<>(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ApiResult<String> nullPointerExceptionHandler(NullPointerException e) {
        log.error("handler NullPointerException. 空指针异常！ msg: -> ", e);
        return ApiResultHandler.failure("内部错误。发生空指针异常！");
    }

    /**
     * 默认服务器异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<Object> defaultErrorHandler(Exception e) {
        log.error("handler defaultException.", e);
        return ApiResultHandler.failure();
    }
}
