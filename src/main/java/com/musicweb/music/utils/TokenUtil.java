package com.musicweb.music.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class TokenUtil {
    private static SecretKey generalKey(){
        String stringKey = "zhiyouwobushigaytamenbujinshigayhaishinvzhuangdalao";//加密的密文
        byte[] encodeKey = Base64.decodeBase64(stringKey);//解码
        //根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        SecretKey key = new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
        return key;
    }

    public static String createToken(Integer userId,String username,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date nowTime = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(userId))
                .setIssuedAt(nowTime)
                .setSubject(username)
                .signWith(signatureAlgorithm,key);
        if (ttlMillis>=0){
            Date exp = new Date(nowMillis + ttlMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims parseToken(String token){
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
