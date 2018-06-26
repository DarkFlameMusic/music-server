package com.musicweb.music.utils;

import com.musicweb.music.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setDesc("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return ResultVOUtil.success(null);
    }

    public static ResultVO success(Integer code,String msg,Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setDesc(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setDesc(msg);
        return resultVO;
    }
}
