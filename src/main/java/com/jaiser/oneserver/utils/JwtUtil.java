/**
 * Copyright (C), 2020, 中电福富信息科技有限公司
 * FileName: JwtUtil
 * Author:   xujiajun
 * Date:     2020/5/17 10:50
 */
package com.jaiser.oneserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Description:
 *
 * @Author xujiajun
 * @Date 2020/5/17  10:50
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static Long jwtExpTime;

    private static String secret;

    private static String ISSUUE = "SERVER";

    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    static final String TOKEN_PREFIX = "Bearer ";        // Token前缀
    static final String AUTHORIZATION = "Authorization";// 存放Token的Header Key


    /**
     * 获取token
     * @param userId
     * @return
     */
    public static String createToken(String userId, String authorities) {


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                /* 设置 载荷 Payload */
//                .withClaim("loginName", "zhuoqianmingyue")
                //            .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                .withClaim("authorities", authorities)
                .withIssuer(ISSUUE)// 签名是有谁生成 例如 服务器
                .withSubject("token")// 签名的主题
                // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的
                .withAudience(userId)// 签名的观众 也可以理解谁接受签名的 存用户id
                .withIssuedAt(new Date()) // 生成签名的时间
                .withExpiresAt(new Date(System.currentTimeMillis() + (jwtExpTime * 1000)))// 签名过期的时间
                /* 签名 Signature */
                .sign(getAlgorithm());

        return TOKEN_PREFIX + token;
    }

    /**
     * 验证token'
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).withIssuer(ISSUUE).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                String key = entry.getKey();
                Claim claim = entry.getValue();
                logger.info("key:" + key + " value:" + claim.asString());
            }
            return true;
        } catch (Exception e) {
            logger.error("验证token失败：", e);
            return false;
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            String tokenStr = request.getHeader(AUTHORIZATION);
            String token = tokenStr.replace(TOKEN_PREFIX, "");

            DecodedJWT jwt = JWT.require(getAlgorithm()).withIssuer(ISSUUE).build().verify(token);
            String userId = jwt.getAudience().get(0);

            Map<String, Claim> claims = jwt.getClaims();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("authorities").asString());

            // 返回验证令牌
            return userId != null ?
                    new UsernamePasswordAuthenticationToken(userId, null, authorities) :
                    null;
        }catch (Exception e) {
            logger.error("鉴权失败", e);
            return null;
        }

    }
    public static Long getJwtExpTime() {
        return jwtExpTime;
    }

    @Value("${jwt-conf.jwt-Exp-Time}")
    public void setJwtExpTime(Long jwtExpTime) {
        JwtUtil.jwtExpTime = jwtExpTime;
    }

    public static String getSecret() {
        return secret;
    }

    @Value("${jwt-conf.secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }
}