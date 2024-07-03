package com.rabbiter.oes.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(name = "登录信息对象", description = "登录对象")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginBody {
    /**
     * 用户名
     */
    @Schema(name = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String account;

    /**
     * 用户密码
     */
    @Schema(name = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度要在8-50之间")
    private String password;

    /**
     * 验证码
     */
    @Schema(name = "验证码")
    private String captchaCode;

    /**
     * 验证码唯一标识
     */
    @Schema(name = "验证码key")
    private String captchaKey;
}
