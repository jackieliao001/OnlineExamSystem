package com.rabbiter.oes.common.util;

/**
 * @author JackieLiao
 * @description
 * @package com.rabbiter.oes.common.util
 * @since 2024/5/23
 */
public class AuthUtils {
    public static boolean isSupperAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
