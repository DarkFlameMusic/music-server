package com.musicweb.music.dao;

import com.musicweb.music.entity.CarouselImgTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarouselImgTbMapper {

    @Update("update carousel_img_tb set carousel_img = #{carouselImg}, carousel_url = #{carouselUrl}, create_time = #{createTime} where carousel_img_id = #{carouselImgId}")
    int updateTestOne(CarouselImgTb carouselImgTb);

    @Select("select * from carousel_img_tb where carousel_img_id = #{carouselImgId}")
    @Results({
            @Result(column = "carousel_img_id", property = "carouselImgId"),
            @Result(column = "carousel_img", property = "carouselImg"),
            @Result(column = "carousel_url", property = "carouselUrl"),
            @Result(column = "create_time", property = "createTime")
    })
    CarouselImgTb findById(Integer carouseImgId);


    List<CarouselImgTb> findAll();

    int insertOne(CarouselImgTb carouselImgTb);


//    @Delete("delete from carousel_img_tb where carousel_img_id=#{carouselImgId}")


    int deleteById(Integer id);

    List<CarouselImgTb> searchByLinkUrl(String content);



}
