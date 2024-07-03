package com.rabbiter.oes.common.core.config;

import cn.hutool.core.util.StrUtil;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.exception.ApiException;
import com.rabbiter.oes.common.exception.TokenException;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.config
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
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> methodArgumentNotValidExceptionHandler(Exception e) {
        log.error("handler MethodArgumentNotValidException.参数异常！ msg : -> ", e);

        List<FieldError> fieldErrors = null;
        if (e instanceof MethodArgumentNotValidException) {
            fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }
        if (e instanceof BindException) {
            fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
        }
        if (fieldErrors == null) {
            return ApiResultHandler.failure(ResponseCode.METHOD_ARGUMENT_NOT_VALID);
        }

        String defaultMessages = fieldErrors.stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(Collectors.joining(StrUtil.COMMA));

        return ApiResultHandler.failure(ResponseCode.METHOD_ARGUMENT_NOT_VALID.getCode(), defaultMessages);
    }

    /**
     * 处理自定义TokenException
     */
    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult<String> unauthorizedExceptionHandler(TokenException e) {
        log.error("handler TokenException.", e);
        return ApiResultHandler.failure(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理自定义ApiException
     */
    @ExceptionHandler(value = ApiException.class)
    public ApiResult<String> apiExceptionHandler(ApiException e) {
        log.error("handler TokenException.", e);
        return ApiResultHandler.failure(e.getErrorCode(), e.getMessage());
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
