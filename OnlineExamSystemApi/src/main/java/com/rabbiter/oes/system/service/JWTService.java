package com.rabbiter.oes.system.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.nimbusds.jose.Payload;
import com.rabbiter.oes.common.core.jwt.JwtPayloadInfo;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * jwt token 接口
 *
 * @author JackieLiao
 * @package com.rabbiter.oes.system.service
 * @since 2024/6/27
 */
public interface JWTService {
    /**
     * 获取 key
     *
     * @return 密钥
     */
    Object genKey();

    /**
     * 对信息进行签名
     *
     * @param payload 荷载信息
     * @return 签名后字符串
     */
    String sign(JwtPayloadInfo payload);

    /**
     * 验证并返回信息
     *
     * @param token 签名后字符串
     * @return 荷载信息
     */
    JwtPayloadInfo verifyAndGetPayload(String token);

    /**
     * 验证token一致性和时效性
     *
     * @param token 签名后字符串
     * @return token是否有效
     */
    default boolean verify(String token) {
        // 失效条件1：token 为 null
        if (StrUtil.isBlank(token)) {
            return false;
        }
        try {
            Date expiredDate = verifyAndGetPayload(token).getExp();
            return Calendar.getInstance().getTime().before(expiredDate);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新token,半小时一次
     *
     * @param oldToken JWT 的 token
     * @return token 是否可以被刷新 true：可以 false：不可以
     */
    default String refreshToken(String oldToken) {
        // 失效条件1：token 为 null
        if (StrUtil.isBlank(oldToken)) {
            return null;
        }
        // 失效条件2：token 无效
        JwtPayloadInfo payloadInfo;
        try {
            payloadInfo = verifyAndGetPayload(oldToken);
        } catch (Exception e) {
            return null;
        }

        // 失效条件3：token 已过期
        Date expiredDate = payloadInfo.getExp();
        if (Calendar.getInstance().getTime().after(expiredDate)) {
            return null;
        }

        // 判断 token 是否可以被刷新
        // 如果 token 在 30 分钟之内刚刷新过，返回原 token

        // 条件1: 当前时间在 token 创建时间之后
        // 条件2: 当前时间在（token 创建时间 + 指定时间段）之前（即指定时间段内可以刷新 token）
        Date refreshDate = new Date();
        Date tokenCreateDate = payloadInfo.getIat();
        if (refreshDate.after(tokenCreateDate) && refreshDate.before(DateUtil.offsetSecond(tokenCreateDate, 30 * 60))) {
            return oldToken;
        } else { // 否则，生成新的 token
            // 根据负载生成新token
            return sign(payloadInfo);
        }
    }

    /**
     * 默认构建payload方法
     *
     * @param payloadInfo payload for JwtPayloadInfo bean
     * @param validTime   jwt有效期限，单位hour
     * @return Payload bean
     * @throws ParseException e
     */
    default Payload buildJWTPayload(JwtPayloadInfo payloadInfo, int validTime) throws ParseException {
        Calendar signTime = Calendar.getInstance();
        Date issueTime = signTime.getTime();
        signTime.add(Calendar.MINUTE, validTime * 60);
        Date expireTime = signTime.getTime();

        payloadInfo.setExp(expireTime);
        payloadInfo.setIat(issueTime);
        payloadInfo.setJti(UUID.fastUUID().toString(true));

        return new Payload(JSON.toJSONString(payloadInfo));
    }

    /**
     * 计算过期时间（单位:秒）
     */
    int getExpiresIn();
}
