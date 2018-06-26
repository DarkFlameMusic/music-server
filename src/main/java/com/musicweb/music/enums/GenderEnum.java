package com.musicweb.music.enums;

public enum GenderEnum {
    UNKNOWN_GENDER(0,"未知性别"),
    MAN(1,"男性"),
    WOMAN(2,"女性")


    ;


    private Integer code;

    private String msg;

    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
