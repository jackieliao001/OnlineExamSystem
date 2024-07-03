package com.rabbiter.oes.common.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码生成配置
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.config
 * @since 2024/7/1
 */
@Configuration
@ConfigurationProperties(prefix = "captcha")
public class KaptchaConfig {
    /** 验证码开关 */
    private String enable;
    /** 验证码过期时间(单位 minute) */
    private String expire;
    /** 验证码类型 */
    private String type;
    /** 验证码长度 */
    private String length;


    @Bean
    DefaultKaptcha producer() {
        Properties properties = new Properties();
        // 图片边框，合法值：yes , no
        properties.put("kaptcha.border", "no");
        // 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue
        properties.put("kaptcha.border.color", "white");
        // 图片高
        properties.put("kaptcha.image.height", "40");
        // 图片宽
        properties.put("kaptcha.image.width", "120");
        // 字体颜色，合法值： r,g,b 或者 white,black,blue.
        properties.put("kaptcha.textproducer.font.color", "black");
        // 文字间隔
        properties.put("kaptcha.textproducer.char.space", "4");
        // 字体大小
        properties.put("kaptcha.textproducer.font.size", "30");
        // 干扰 颜色，合法值： r,g,b 或者 white,black,blue
        properties.put("kaptcha.noise.color", "120");
        Config config = new Config(properties);

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
