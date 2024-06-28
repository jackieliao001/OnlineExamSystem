package com.rabbiter.oes.core.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.core.jwt
 * @since 2024/6/27
 */
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtPayloadInfo {
    // "主题" SUBJECT
    private String sub;

    // "签发时间" ISSUED_AT
    private Date iat;

    // 过期时间 EXPIRATION_TIME
    private Date exp;

    // JWT的ID JWT_ID
    private String jti;

    // "用户名称"
    private String username;

    // "用户拥有的权限"
    private List<String> authorities;

}
