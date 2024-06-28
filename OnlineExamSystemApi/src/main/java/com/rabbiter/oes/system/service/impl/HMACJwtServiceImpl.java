package com.rabbiter.oes.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.exception.TokenException;
import com.rabbiter.oes.core.jwt.JwtPayloadInfo;
import com.rabbiter.oes.system.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;


/**
 * 基于对称加密(HMAC SHA-256) 算法实现
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.system.service.impl
 * @since 2024/6/27
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "jwt.algorithm", havingValue = "HMAC")
public class HMACJwtServiceImpl implements JWTService {

    // JWT 过期时间(hours),默认3小时
    @Value("${jwt.expirationTime: 3}")
    private int validTime;
    // JWT 密钥
    @Value("${jwt.secretKey: drama@2024}")
    private String secretKey;
    private String secret;

    /**
     * 生成密钥
     * 将参数secretKey作为密钥因子，使用SHA-256算法生成密钥
     *
     * @return secret str
     */
    @Override
    public Object genKey() {
        if (StrUtil.isNotBlank(secret)) {
            return secret;
        }

        // 对输入因子进行编码
        try {
            byte[] encodedFactor = secretKey.getBytes(StandardCharsets.UTF_8);
            // 获取SHA-256实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 计算SHA-256摘要,将摘要转换为64位的十六进制字符串
            secret = Arrays.toString(digest.digest(encodedFactor));
        } catch (NoSuchAlgorithmException e) {
            log.error("get token KEY error. SHA-256 algorithm not found", e);
            throw new TokenException(ResponseCode.TOKEN_ERROR, e);
        }
        return secret;
    }

    @Override
    public String sign(JwtPayloadInfo payload) {
        return signDirectByHMAC(payload, String.valueOf(genKey()));
    }

    @Override
    public JwtPayloadInfo verifyAndGetPayload(String token) {
        String payload = verifyDirectByHMAC(token, String.valueOf(genKey()));
        JwtPayloadInfo payloadInfo = JSON.parseObject(payload, JwtPayloadInfo.class);
        return payloadInfo;
    }

    /**
     * 使用 摘要算法(HMAC SHA-256)签名信息（Payload 中只包含私有信息）
     *
     * @param payloadInfo 荷载信息
     * @param key         密钥
     * @return 签名后的信息
     */
    private String signDirectByHMAC(JwtPayloadInfo payloadInfo, String key) {
        try {
            // 创建头部对象
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            // 建立一个载荷 Payload
            Payload payload = buildJWTPayload(payloadInfo, validTime);

            // 将头部和载荷结合在一起
            JWSObject signedJWT = new JWSObject(jwsHeader, payload);
            // 建立一个密匙
            JWSSigner jwsSigner = new MACSigner(key);
            // 签名
            signedJWT.sign(jwsSigner);
            // 生成 token
            return signedJWT.serialize();
        } catch (JOSEException | ParseException e) {
            log.error("生成HMAC签名Token失败", e);
            throw new TokenException(ResponseCode.TOKEN_ERROR, e);
        }
    }


    /**
     * 使用 HMAC 算法验证并获取ClaimsSet信息
     *
     * @param token token字符串
     * @param key   密钥
     * @return 验证解密后的荷载信息
     */
    private String verifyDirectByHMAC(String token, String key) {
        try {
            // 建立一个解锁密匙
            JWSVerifier jwsVerifier = new MACVerifier(key);
            JWSObject parse = JWSObject.parse(token);
            if (parse.verify(jwsVerifier)) {
                return parse.getPayload().toString();
            }
            throw new TokenException(ResponseCode.TOKEN_ERROR, "Payload can not be null");
        } catch (Exception e) {
            throw new TokenException(ResponseCode.TOKEN_ERROR, e);
        }
    }
}
