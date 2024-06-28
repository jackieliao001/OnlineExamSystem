package com.rabbiter.oes.config;

import cn.hutool.core.util.StrUtil;
import com.rabbiter.oes.common.enums.ErrorCode;
import com.rabbiter.oes.common.exception.TokenException;
import com.rabbiter.oes.system.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器,登录检查
 * <p>
 * 实现HandlerInterceptor接口
 * </p>
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.config
 * @since 2024/6/27
 */
@Component
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    /**
     * 令牌前缀
     * Authorization 请求头中的 token 字符串的开头部分（Bearer）
     */
    public static final String PREFIX_BEARER = "Bearer ";

    /**
     * token在 HTTP HEADER 中默认的 KEY名称
     */
    @Value("${jwt.tokenName: drama_token}")
    private String tokenName;

    @Resource
    private JWTService jwtService;

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义 Controller
     * 返回值：
     * true 表示继续流程（如调用下一个拦截器或处理器）；
     * false 表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过 response 来产生响应。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return 是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取 header 中的 Authorization 信息
        String token = request.getHeader("Authorization");
        log.info("token: {}", token);
        if (StrUtil.isBlank(token) || token.trim().equals(PREFIX_BEARER.trim())) {
            token = request.getHeader(tokenName);
        } else {
            token = token.replace(PREFIX_BEARER, "").trim();
        }

        if (StrUtil.isBlank(token)) {
            log.error("token 验证失败！token is blank, uri is {}", request.getRequestURI());
            throw new TokenException(ErrorCode.TOKEN_NOT_FOUND);
        }

        boolean isValid = jwtService.verify(token);
        return isValid;
    }
}
