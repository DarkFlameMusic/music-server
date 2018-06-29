package com.musicweb.music.dao;

import com.musicweb.music.entity.SingerTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SingerTbMapper {

//    @Insert("insert into singer_tb(singer_name,singer_img,user_id," +
//            "singer_one_intro,singer_intro) " +
//            "values(#{singerName,jdbcType=VARCHAR}," +
//            "#{userId,jdbcType=INTEGER}#{singerImg,jdbcType=VARCHAR}," +
//            "#{singerOneIntro,jdbcType=VARCHAR},#{singerIntro,jdbcType=VARCHAR})")
    int insertOne(SingerTb singerTb);

    SingerTb findById(Integer id);

    List<SingerTb> findAll();


    List<SingerTb> findAllInSinger();

    List<SingerTb> findByType(Integer type);

    List<SingerTb> findByTypeAndInitial(Integer singerType,String initial);

    @Update("update singer_tb set " +
            "singer_id=#{singerId},user_id=#{userId},singer_name=#{singerName}," +
            "singer_img=#{singerImg},singer_one_intro=#{singerOneIntro}," +
            "singer_intro=#{singerIntro} " +
            "where singer_id=#{singerId}")
    int updateOne(SingerTb singerTb);

    @Delete("delete from singer_tb where singer_id=#{singerId}")
    int deleteById(Integer id);

    List<SingerTb> searchBySingerName(String content);

    SingerTb findByUserId(Integer userId);
}
