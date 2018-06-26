package com.musicweb.music.enums;

import com.musicweb.music.exception.MusicException;

public enum UserJurisdictionEnum {

    ADMIN(0,"admin"),
    USER(1,"user"),
    SINGER(2,"singer"),
    WAIT(3,"未激活"),
    iDENTIFY_REJECT(4,"认证被驳回"),
    WAIT_iDENTIFY(5,"等待认证")


    ;


    private Integer code;

    private String msg;

    UserJurisdictionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getSting(Integer code){
        for (UserJurisdictionEnum userJurisdictionEnum: values()){
            if (userJurisdictionEnum.getCode() == code){
                return userJurisdictionEnum.getMsg();
            }
        }
        throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
