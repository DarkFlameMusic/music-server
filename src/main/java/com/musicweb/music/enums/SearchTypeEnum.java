package com.musicweb.music.enums;

public enum SearchTypeEnum {
    SONG(1000,"单曲"),
    SINGER(1001,"歌手"),
    ALBUM(1002,"专辑"),
    MV(1003,"MV"),
    LYRIC(1004,"歌词"),
    SONG_LIST(1005,"歌单"),
    USER(1006,"用户")
    ;


    private Integer code;

    private String msg;

    SearchTypeEnum(Integer code, String msg) {
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
