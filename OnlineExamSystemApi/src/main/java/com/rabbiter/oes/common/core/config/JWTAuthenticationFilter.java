package com.rabbiter.oes.common.core.config;

import com.rabbiter.oes.common.domain.UserDetail;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.exception.ApiException;
import com.rabbiter.oes.common.core.jwt.JwtPayloadInfo;
import com.rabbiter.oes.common.core.manager.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
@Slf4j
@Component
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private final TokenManager tokenManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
    }


    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆）
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 处理器
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JWTAuthenticationFilter 请求地址:{}", request.getRequestURI());
        //获取 header 中的token
        String token = tokenManager.getTokenFromRequestHeader(request);
        if (!StringUtils.hasText(token)) {
            // 如果请求头中没有Authorization信息则直接放行了
            filterChain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        JwtPayloadInfo payloadInfo = tokenManager.verifyAndGetPayload(token);
        String userId = payloadInfo.getSub();
        //从redis中获取用户信息
        UserDetail loginUser = tokenManager.getCacheLoginUser(userId);
        if (Objects.isNull(loginUser)) {
            throw new ApiException(ResponseCode.TOKEN_NOT_FOUND);
        }
        //存入SecurityContextHolder
        // 创建验证通过的令牌对象,封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        // 设置令牌到安全上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
