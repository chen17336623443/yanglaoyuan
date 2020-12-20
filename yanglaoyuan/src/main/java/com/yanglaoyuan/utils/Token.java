package com.yanglaoyuan.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {
    private static String TOKEN_SECRET = "hkasahfhsofrinfnsuihfisnfkewnknfahi";

    /**
     * 生成token
     *
     * @return
     */
    public static String createToken(String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Date date = new Date();
        return JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withClaim("username", name)
                .withIssuer("theAngel")//签名是有谁生成 例如 服务器
                .withSubject("this is log token")//签名的主题
                .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                .withAudience(name)//签名的观众 也可以理解谁接受签名的
                .withIssuedAt(date) //生成签名的时间
                .withExpiresAt(getDate(date))// 签名过期的时间
                .sign(algorithm);//签名 Signature
    }

    /**
     * 判断token是否正确
     *
     * @param token
     * @return
     */
    public static boolean tokeneq(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {  //InvalidClaimException登录超时
            return false;
        } catch (JWTVerificationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }


    /**
     * 过期时间
     *
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }
}
