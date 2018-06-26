package com.musicweb.music.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

public class MD5Util {

    private final String SALT="ehji12y3*&*(&(#dsad**-/*";
    public static String encode(String password){
        password = password + "zhiyouwobushigay";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i<charArray.length;i++){
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0;i<md5Bytes.length;i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val<16){
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    //TODO 未测试
    public static String getMD5(String content){
        String md5 = DigestUtils.md5DigestAsHex(content.getBytes());
        return md5;
    }

//    public static void main(String[] args){
//        String content = "123456";
//        System.out.println(getMD5(content));
//        System.out.println(encode(content));
//    }
}
