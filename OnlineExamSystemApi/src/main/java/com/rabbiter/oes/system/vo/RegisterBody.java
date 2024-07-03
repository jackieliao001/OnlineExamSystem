package com.rabbiter.oes.system.vo;

import com.rabbiter.oes.common.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户注册body
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.system.vo
 * @since 2024/5/22
 */
@Schema(name = "用户注册信息对象", description = "用户注册对象")
@Validated
@Data
public class RegisterBody {
    @Schema(name = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @Schema(name = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度要在8-50之间")
    private String password;

    @Schema(name = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    @Size(min = 11, max = 14, message = "手机号码错误")
    private String tel;

    /**
     * 性别:1男，2女，3未知
     */
    @Schema(name = "性别", description = "性别:1男，2女，3未知", allowableValues = {"MAN", "WOMAN", "UNKNOWN"})
    private SexEnum sex;

    @Schema(name = "身份证号")
    private String idCard;

    @Schema(name = "邮箱")
    @Email
    private String email;
}
