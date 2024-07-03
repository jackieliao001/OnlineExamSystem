package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import com.rabbiter.oes.common.constant.Constants;
import com.rabbiter.oes.common.core.manager.PasswordManager;
import com.rabbiter.oes.common.core.manager.TokenManager;
import com.rabbiter.oes.common.domain.UserDetail;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.enums.UserStatusEnum;
import com.rabbiter.oes.common.enums.UserTypeEnum;
import com.rabbiter.oes.common.exception.ApiException;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.common.util.RedisCacheUtils;
import com.rabbiter.oes.system.entity.SysUser;
import com.rabbiter.oes.system.service.SysLoginService;
import com.rabbiter.oes.system.service.SysUserService;
import com.rabbiter.oes.system.vo.LoginBody;
import com.rabbiter.oes.system.vo.RegisterBody;
import com.rabbiter.oes.system.vo.TokenInfoBO;
import com.rabbiter.oes.system.vo.TokenInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class SysLoginServiceImpl implements SysLoginService {
    private final SysUserService userService;
    //    private final SysPermissionService permissionService;
    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;
    private final PasswordManager passwordManager;
    private final Producer producer;
    private final RedisCacheUtils redisCacheUtils;

    @Override
    public ApiResult<TokenInfoVO> login(LoginBody login) {
        //验证码校验
//        validateCaptcha(login.getCaptchaCode(), login.getCaptchaKey());

        // 密码校对
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getAccount(), login.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new ApiException(ResponseCode.ACCOUNT_PASSWORD_ERROR);
        }

        //使用userid生成token
        UserDetail loginUser = (UserDetail) authenticate.getPrincipal();
        SysUser sysUser = loginUser.getUser();
        if (UserStatusEnum.OK != sysUser.getStatus()) {
            // 用户已禁用，请联系客服
            throw new ApiException(ResponseCode.ACCOUNT_DISABLED);
        }

        Long userId = sysUser.getUserId();

        // 生成token，存储redis
        TokenInfoBO tokenInfoBO = TokenInfoBO.builder().userId(userId).account(sysUser.getAccount()).build();
        TokenInfoVO tokenInfoVO = tokenManager.storeAccessToken(tokenInfoBO);
        //authenticate存入redis
        tokenManager.storeLoginUser(userId, loginUser);

        return ApiResultHandler.success(tokenInfoVO);
    }

    private void validateCaptcha(String code, String key) {
        String cacheMapValue = redisCacheUtils.getCacheMapValue(Constants.CAPTCHA_KEY, key);
        if (Objects.isNull(cacheMapValue)) {
            throw new ApiException(ResponseCode.CAPTCHA_ERROR);
        }
        if (!cacheMapValue.equals(code)) {
            throw new ApiException(ResponseCode.CAPTCHA_EXPIRED);
        }
        // 验证码正确，删除已使用验证码
        redisCacheUtils.delCacheMapValue(Constants.CAPTCHA_KEY, key);
    }

    @Override
    public ApiResult<String> register(RegisterBody registerParam) {
        // todo 注册信息预检测

        // 存储用户信息
        String encodePwd = passwordManager.encryptPassword(registerParam.getPassword());
        SysUser sysUser = new SysUser();
        sysUser.setAccount(userService.generateNewAccount(registerParam.getTel()));
        sysUser.setPwd(encodePwd);
        sysUser.setTel(registerParam.getTel());
        sysUser.setNickName(registerParam.getNickName());
        sysUser.setUserType(UserTypeEnum.REGISTER);
        sysUser.setStatus(UserStatusEnum.OK);
        sysUser.setCreateBy("register");
        sysUser.setModifiedBy("register");
        boolean save = userService.save(sysUser);
        return save ? ApiResultHandler.success() : ApiResultHandler.failure();
    }

    @Override
    public ApiResult<Object> logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail loginUser = (UserDetail) authentication.getPrincipal();
        // 清除token
        tokenManager.clearLoginUser(loginUser.getUser().getUserId());
        return ApiResultHandler.success();
    }

    @Override
    public ApiResult<Map<Object, Object>> genCaptcha() {
        // 生成验证码
        String code = producer.createText();
        // 生成图片
        BufferedImage image = producer.createImage(code);

        String base64Img;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            //输出流输出图片，格式为jpg
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();

            // 图片转base64
            BASE64Encoder encoder = new BASE64Encoder();
            base64Img = encoder.encode(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("验证码图片生成失败", e);
            throw new ApiException(ResponseCode.CAPTCHA_GEN_EXCEPTION, e);
        }
        base64Img = "data:image/jpeg;base64," + base64Img;
        // 缓存验证码，有效期3分钟
        String key = IdUtil.getSnowflake().nextIdStr();
        redisCacheUtils.setCacheMapValue(Constants.CAPTCHA_KEY, key, code, 180);

        return ApiResultHandler.success(
                MapUtil.builder()
                        .put("captchaKey", key)
                        .put("captchaImg", base64Img)
                        .build()
        );
    }


    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr());
//        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateById(sysUser);
    }
}
