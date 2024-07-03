package com.rabbiter.oes.system.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用户token携带信息BO。该信息用于生成BO的时候使用
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.system.vo
 * @since 2024/6/28
 */
@Data
@Builder
public class TokenInfoBO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 登录账号
     */
    private String account;
}
