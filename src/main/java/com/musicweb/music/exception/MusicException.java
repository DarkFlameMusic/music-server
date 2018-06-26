package com.musicweb.music.exception;

import com.musicweb.music.enums.ExceptionEnum;

public class MusicException extends  RuntimeException{

    private Integer code;

    public MusicException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
