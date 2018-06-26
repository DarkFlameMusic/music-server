package com.musicweb.music.utils;

public class IntegerUtil {

    public static String to(Integer number){
        if (number == null){
            number =0;
        }else if (number >=100000){
            Double n = Double.valueOf(String.valueOf(number));
            Double m = 10000.0;
            return  n/m+ "万";
        }else{
            return String.valueOf(number);
        }
        return "0";
    }
}
