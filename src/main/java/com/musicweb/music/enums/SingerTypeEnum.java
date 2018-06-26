package com.musicweb.music.enums;

import com.musicweb.music.exception.MusicException;

public enum SingerTypeEnum {
    CHINESE_MAN(1,"华语男歌手"),
    CHINESE_WOMAN(2,"华语女歌手"),
    CHINESE_GROUP(3,"华语组合或乐队"),
    OCCIDENT_MAN(4,"欧美男歌手"),
    OCCIDENT_WOMAN(5,"欧女歌手"),
    OCCIDENT_GROUP(6,"欧美组合或乐队"),
    JAPAN_MAN(7,"日本男歌手"),
    JAPAN_WOMAN(8,"日本女歌手"),
    JAPAN_GROUP(9,"日本组合或乐队"),
    KOREA_MAN(10,"韩国男歌手"),
    KOREA_WOMAN(11,"韩国女歌手"),
    KOREA_GROUP(12,"韩国组合或乐队"),
    OTHER_MAN(13,"其他男歌手"),
    OTHER_WOMAN(14,"其他女歌手"),
    OTHER_GROUP(15,"其他组合或乐队")

    ;


    private Integer code;

    private String msg;

    SingerTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

//    public static String getString(Integer code){
//        if (code == CHINESE_MAN.getCode()){
//            return CHINESE_MAN.getDesc();
//        }else if (code == CHINESE_WOMAN.getCode()){
//            return CHINESE_WOMAN.getDesc();
//        }else if (code == CHINESE_GROUP.getCode()){
//            return CHINESE_GROUP.getDesc();
//        }else if (code == OCCIDENT_MAN.getCode()){
//            return OCCIDENT_MAN.getDesc();
//        }else if (code == OCCIDENT_WOMAN.getCode()){
//            return OCCIDENT_WOMAN.getDesc();
//        }else if (code == OCCIDENT_GROUP.getCode()){
//            return OTHER_GROUP.getDesc();
//        }else if (code == JAPAN_MAN.getCode()){
//            return JAPAN_MAN.getDesc();
//        }else if (code == JAPAN_WOMAN.getCode()){
//            return JAPAN_WOMAN.getDesc();
//        }else if (code == JAPAN_GROUP.getCode()){
//            return JAPAN_GROUP.getDesc();
//        }else if (code == KOREA_MAN.getCode()){
//            return KOREA_MAN.getDesc();
//        }else if (code == KOREA_WOMAN.getCode()){
//            return KOREA_WOMAN.getDesc();
//        }else if (code == KOREA_GROUP.getCode()){
//            return KOREA_GROUP.getDesc();
//        }else if (code == OTHER_MAN.getCode()){
//            return OTHER_MAN.getDesc();
//        }else if (code == OTHER_WOMAN.getCode()){
//            return OTHER_WOMAN.getDesc();
//        }else if (code == OTHER_GROUP.getCode()){
//            return OTHER_GROUP.getDesc();
//        }else {
//            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
//        }
//    }

    public static String getSting(Integer code){
        for (SingerTypeEnum singerTypeEnum: values()){
            if (singerTypeEnum.getCode() == code){
                return singerTypeEnum.getMsg();
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
