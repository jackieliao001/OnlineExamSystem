package com.rabbiter.oes.common.core.config;

import com.rabbiter.oes.common.core.manager.TokenManager;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.config
 * @since 2024/7/1
 */
@Slf4j
@Component
public class LogoutHandler implements LogoutSuccessHandler {
    private final TokenManager tokenManager;

    public LogoutHandler(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 清理用户token和缓存
        String token = tokenManager.getTokenFromRequestHeader(request);
        if (token != null) {
            tokenManager.removeToken(token);

            //清空缓存中当前用户的数据
            String userId = tokenManager.verifyAndGetPayload(token).getSub();
            tokenManager.clearLoginUser(userId);
        }

        log.debug("LogoutHandler do onLogoutSuccess：注销成功,退出登录");
        ApiResult<String> result = ApiResultHandler.success("Logout");

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JacksonUtils.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
