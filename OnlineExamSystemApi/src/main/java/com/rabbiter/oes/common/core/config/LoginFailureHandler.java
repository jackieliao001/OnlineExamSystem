package com.rabbiter.oes.common.core.config;

import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.exception.CaptchaException;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证失败处理器
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.config
 * @since 2024/7/1
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("LoginFailureHandler do onAuthenticationFailure");
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        ApiResult<String> result;
        if (exception instanceof CaptchaException) {
            result = ApiResultHandler.failure(ResponseCode.CAPTCHA_ERROR);
        } else {
            result = ApiResultHandler.failure(ResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        outputStream.write(JacksonUtils.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
