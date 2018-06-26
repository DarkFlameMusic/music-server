package com.musicweb.music.enums;

import com.musicweb.music.exception.MusicException;

public enum CommentTypeEnum {
    SONG_COMMENT(0,"歌曲评论"),
    SONG_LIST_COMMENT(1,"歌单评论"),
    ALBUM_COMMENT(2,"专辑评论")
    ;



    private Integer code;

    private String msg;

    CommentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String  getString(Integer code){
        if (code == 0){
            return SONG_COMMENT.getMsg();
        }else if (code == 1){
            return SONG_LIST_COMMENT.getMsg();
        }else if (code == 2){
            return ALBUM_COMMENT.getMsg();
        }else
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
