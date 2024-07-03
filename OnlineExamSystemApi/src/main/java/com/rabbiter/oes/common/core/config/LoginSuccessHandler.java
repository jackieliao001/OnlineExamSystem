package com.rabbiter.oes.common.core.config;

import com.rabbiter.oes.common.core.manager.TokenManager;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.util.JacksonUtils;
import com.rabbiter.oes.system.vo.TokenInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证成功处理器
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.config
 * @since 2024/7/1
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenManager tokenManager;

    public LoginSuccessHandler(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("LoginSuccessHandler do onAuthenticationSuccess");
        // 生成JWT，并放置到请求头中
        TokenInfoBO tokenInfoBO = TokenInfoBO.builder().account(authentication.getName()).build();
        String jwt = tokenManager.storeAccessToken(tokenInfoBO).getAccessToken();
        response.setHeader(tokenManager.getTokenHeaderName(), jwt);

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ApiResult<String> result = ApiResultHandler.success("Success Login");
        outputStream.write(JacksonUtils.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
