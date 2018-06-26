package com.musicweb.music.dao;

import com.musicweb.music.entity.UserTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserTbMapper {


    int insertForSignIn(UserTb userTb);


    int updateByUserId(UserTb userTb);

    @Select("select * from user_tb where user_id=#{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_nickname", property = "userNickname"),
            @Result(column = "mail", property = "mail"),
            @Result(column = "jurisdiction", property = "jurisdiction"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "head_img", property = "headImg"),
            @Result(column = "birth_date", property = "birthDate"),
            @Result(column = "area", property = "area"),
            @Result(column = "person_intro", property = "personIntro"),
            @Result(column = "attention_number", property = "attentionNumber"),
            @Result(column = "fan_number", property = "fanNumber"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    UserTb findByUserId(Integer id);

    UserTb findByUsername(String username);

    List<UserTb> findByUserNickname(String userNickname);

    List<UserTb> findByJurisdiction(Integer jurisdiction);

    UserTb findByCaptcha(String captcha);

    List<UserTb> searchByUserNickname(String content);

    @Delete("delete from user_tb where user_id=#{userId}")
    int deleteById(Integer id);

}
