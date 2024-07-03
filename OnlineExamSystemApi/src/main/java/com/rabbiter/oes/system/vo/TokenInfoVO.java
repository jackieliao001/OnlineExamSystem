package com.rabbiter.oes.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * token信息。该信息返回给前端，前端请求携带accessToken进行用户校验
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.system.vo
 * @since 2024/6/28
 */
@Schema(name = "认证Token信息")
@Data
public class TokenInfoVO {
    @Schema(name = "认证token", description = "用于权限认证")
    private String accessToken;

    @Schema(name = "刷新token", description = "用于认证token刷新")
    private String refreshToken;

    @Schema(name = "token过期时长", description = "单位秒")
    private Integer expiresIn;
}
