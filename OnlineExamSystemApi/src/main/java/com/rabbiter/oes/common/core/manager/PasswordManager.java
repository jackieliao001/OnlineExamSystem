package com.rabbiter.oes.common.core.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 密码管理器
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.common.core.manager
 * @since 2024/6/28
 */
@Slf4j
@Component
public class PasswordManager {
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 加密密码
     *
     * @param rawPassword 明文密码
     */
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 解密密码
     *
     * @param encodedPassword 密文密码
     */
    public String decryptPassword(String encodedPassword) {
        return null;
    }

    /**
     * 密码校验
     *
     * @param rawPassword     明文密码
     * @param encodedPassword 密文密码
     */
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
