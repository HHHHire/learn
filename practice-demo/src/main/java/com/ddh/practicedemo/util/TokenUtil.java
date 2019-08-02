package com.ddh.practicedemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author ddh
 * @date 2019/8/2 15:19
 * @description Token 工具类
 **/

@Component
@PropertySource("classpath:config/jwt-config.properties")
public class TokenUtil {
    /**
     * 设置 token 的过期时间一年
     */
    private static final long EXPIRES_TIME = 31536000;
    private final Environment env;

    public TokenUtil(Environment env) {
        this.env = env;
    }

    /**
     * 创建 jwt 串
     *
     * @param map 自定义的 key, value
     * @return String
     * @throws JWTCreationException s
     */
    public String createJwt(Map<String, String> map) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret-key")));

        JWTCreator.Builder builder = JWT.create()
                .withIssuer(env.getProperty("jwt.issuer"))
                .withSubject(env.getProperty("jwt.subject"))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_TIME));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }
        return builder.sign(algorithm);
    }

    public Claim getClaim(String token, String key)throws JWTVerificationException {
        DecodedJWT jwt = getDecoded(token);
        return jwt.getClaim(key);
    }
    /**
     * 检测token是否有效
     *
     * @param token token串
     * @return Boolean
     */
    public Boolean isValid(String token) {
        if (token == null || "".equals(token)) {
            return false;
        } else {
            try {
                getDecoded(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 获取 decoded
     *
     * @param token token串
     * @return DecodedJWT
     */
    private DecodedJWT getDecoded(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret-key")));
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
