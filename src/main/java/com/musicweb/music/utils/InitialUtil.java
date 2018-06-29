package com.musicweb.music.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Component;

@Component
public class InitialUtil {
    private HanyuPinyinOutputFormat format = null;

    private String[] pinyin;

    public InitialUtil() {
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyin = null;
    }

    public String getCharPinYin(char pinYinStr){

        try
        {
            //执行转换
            pinyin = PinyinHelper.toHanyuPinyinStringArray(pinYinStr, format);

        } catch (BadHanyuPinyinOutputFormatCombination e)
        {
            e.printStackTrace();
        }

        //pinyin4j规则，当转换的符串不是汉字，就返回null
        if(pinyin == null){
            return null;
        }
        //多音字会返回一个多音字拼音的数组，pinyiin4j并不能有效判断该字的读音
        return pinyin[0];
    }

    public String getStringPinyin(String pinYinStr){
        StringBuffer stringBuffer = new StringBuffer();
        String temStr = null;
        for(int i = 0; i < pinYinStr.length(); i++){
            temStr = this.getCharPinYin(pinYinStr.charAt(i));
            if(temStr == null){
                stringBuffer.append(pinYinStr.charAt(i));
            }
            else{
                stringBuffer.append(temStr);
            }
        }
        return stringBuffer.toString();
    }

}
