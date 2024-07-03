package com.rabbiter.oes.common.core.manager;

import cn.hutool.core.util.StrUtil;
import com.rabbiter.oes.common.constant.Constants;
import com.rabbiter.oes.common.core.jwt.JwtPayloadInfo;
import com.rabbiter.oes.common.domain.UserDetail;
import com.rabbiter.oes.common.util.RedisCacheUtils;
import com.rabbiter.oes.system.service.JWTService;
import com.rabbiter.oes.system.vo.TokenInfoBO;
import com.rabbiter.oes.system.vo.TokenInfoVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.manager
 * @since 2024/6/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TokenManager {

    /**
     * 令牌前缀
     * Authorization 请求头中的 token 字符串的开头部分（Bearer）
     */
    public static final String PREFIX_BEARER = "Bearer ";
    /**
     * token在 HTTP HEADER 中默认的 KEY名称
     */
    @Getter
    @Value("${jwt.tokenName: drama_token}")
    private String tokenHeaderName;

    private final JWTService jwtService;
    private final RedisCacheUtils redisCacheUtils;

    public String getTokenFromRequestHeader(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token) || token.trim().equals(PREFIX_BEARER.trim())) {
            token = request.getHeader(tokenHeaderName);
        } else {
            token = token.replace(PREFIX_BEARER, "").trim();
        }
        log.info("get header token: {}", token);

        return token;
    }

    /**
     * 技术生成token，并返回token信息
     *
     * @param tokenInfoBO 用户信息
     * @return token
     */
    public TokenInfoVO storeAccessToken(TokenInfoBO tokenInfoBO) {
        // token生成
        JwtPayloadInfo build = JwtPayloadInfo.builder()
                .sub(String.valueOf(tokenInfoBO.getUserId()))
                .username(tokenInfoBO.getAccount())
                .build();
        String token = jwtService.sign(build);
        // 获取过期时间
        int timeoutSecond = jwtService.getExpiresIn();
        // token信息存入缓存
/*        String keyName = OauthCacheNames.USER_INFO + token;
        redisCacheUtils.deleteObject(keyName);
        redisCacheUtils.setCacheSet(keyName, JSON.toJSONString(tokenInfoBO));*/

        // 数据封装返回(token不用加密)
        TokenInfoVO tokenInfoVO = new TokenInfoVO();
        tokenInfoVO.setAccessToken(token);
        tokenInfoVO.setRefreshToken(token);
        tokenInfoVO.setExpiresIn(timeoutSecond);

        return tokenInfoVO;
    }

    public JwtPayloadInfo verifyAndGetPayload(String token) {
        return jwtService.verifyAndGetPayload(token);
    }

    public void removeToken(String token) {
        // 删除缓存中token信息
    }

    /**
     * @param userId    用户ID
     * @param loginUser 用户信息
     * @description 缓存存储用户信息
     */
    public void storeLoginUser(Object userId, UserDetail loginUser) {
        redisCacheUtils.setCacheObject(Constants.LOGIN_USER_KEY + userId, loginUser);
    }

    /**
     * @param userId 用户ID
     * @description 缓存存储用户信息
     */
    public UserDetail getCacheLoginUser(Object userId) {
        return redisCacheUtils.getCacheObject(Constants.LOGIN_USER_KEY + userId);
    }

    /**
     * @param userId 用户ID
     * @description 删除用户缓存记录
     */
    public void clearLoginUser(Object userId) {
        redisCacheUtils.deleteObject(Constants.LOGIN_USER_KEY + userId);
    }
}
