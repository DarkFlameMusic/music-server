package com.musicweb.music.utils;

import com.musicweb.music.entity.UserTb;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.exception.MusicException;

public class VerifyUserUtil {

    public static void verifyUser(UserTb userTb,String password){
        if (userTb == null){
            throw new MusicException(ExceptionEnum.USERNAME_ERROR);
        }
        if (!userTb.getPassword().equals(password)){
            throw new MusicException(ExceptionEnum.PASSWORD_ERROR);
        }
    }
    public static void verifyCaptcha(UserTb userTb){
        if (userTb == null){
            throw new MusicException(ExceptionEnum.CAPTCHA_ERROR);
        }else{
            userTb.setCaptcha(null);
        }
    }

    public static void verifyToken(UserTb userTb){
        if(userTb == null){
            throw new MusicException(ExceptionEnum.TOKEN_ERROR);
        }
    }

    public static void verifyNotNull(UserTb userTb){
        if(userTb == null){
            throw new MusicException(ExceptionEnum.DATA_NULL);
        }
    }
}
