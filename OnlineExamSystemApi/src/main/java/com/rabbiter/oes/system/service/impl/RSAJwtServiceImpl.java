package com.rabbiter.oes.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.rabbiter.oes.common.enums.ErrorCode;
import com.rabbiter.oes.common.exception.TokenException;
import com.rabbiter.oes.core.jwt.JwtPayloadInfo;
import com.rabbiter.oes.system.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

/**
 * @author JackieLiao
 * @package com.rabbiter.oes.system.service.impl
 * @since 2024/6/28
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "jwt.algorithm", havingValue = "RSA")
public class RSAJwtServiceImpl implements JWTService {

    // JWT 过期时间(hours),默认3小时
    @Value("${jwt.expirationTime: 3}")
    private int validTime;
    // JWT 密钥
    @Value("${jwt.secretKey: drama@2024}")
    private String secretKey;
    private RSAKey rsaKey;

    @Override
    public Object genKey() {
        if (rsaKey != null) {
            return rsaKey;
        }
        try {
            SecureRandom secureRandom = new SecureRandom(secretKey.getBytes(StandardCharsets.UTF_8));
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        } catch (NoSuchAlgorithmException e) {
            log.error("get token KEY error. 生成RSA密钥对失败", e);
        }
        return rsaKey;
    }

    @Override
    public String sign(JwtPayloadInfo payload) {
        return signDirectByRSA(payload, (RSAKey) genKey());
    }

    @Override
    public JwtPayloadInfo verifyAndGetPayload(String token) {
        String payload = verifyDirectByRSA(token, (RSAKey) genKey());
        return JSON.parseObject(payload, JwtPayloadInfo.class);
    }

    /**
     * 使用 RSA 算法签名信息（Payload 中只包含私有信息）
     *
     * @param info   payload信息
     * @param rsaKey rsa密钥对
     * @return
     */
    private String signDirectByRSA(JwtPayloadInfo info, RSAKey rsaKey) {
        try {
            JWSSigner signer = new RSASSASigner(rsaKey);
            Payload payload = buildJWTPayload(info, validTime);
            JWSObject jwsObject = new JWSObject(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                    payload
            );
            // 进行加密
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException | ParseException e) {
            log.error("生成RSA签名Token失败", e);
            throw new TokenException(ErrorCode.TOKEN_ERROR, e);
        }
    }

    /**
     * 使用 RSA 算法验证 token（Payload 中只包含私有信息）
     *
     * @param token  token
     * @param rsaKey 密钥对
     * @return 验证解密后的payload
     */
    private String verifyDirectByRSA(String token, RSAKey rsaKey) {
        try {
            RSAKey publicRSAKey = rsaKey.toPublicJWK();
            JWSVerifier jwsVerifier = new RSASSAVerifier(publicRSAKey);
            JWSObject jwsObject = JWSObject.parse(token);
            // 验证数据
            if (jwsObject.verify(jwsVerifier)) {
                return jwsObject.getPayload().toString();
            }
            throw new TokenException(ErrorCode.TOKEN_ERROR, "Payload can not be null");
        } catch (Exception e) {
            throw new TokenException(ErrorCode.TOKEN_ERROR, e);
        }
    }
}
