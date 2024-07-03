package com.rabbiter.oes.common.core.config;

import com.alibaba.fastjson2.JSON;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.config
 * @since 2024/7/1
 */
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("AccessDeniedHandler do handle");
        ApiResult<String> result = ApiResultHandler.failure(ResponseCode.FORBIDDEN);
        String json = JSON.toJSONString(result);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(json);
        response.getWriter().flush();
    }
}
