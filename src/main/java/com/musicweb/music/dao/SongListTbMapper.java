package com.musicweb.music.dao;

import com.musicweb.music.entity.SongListTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SongListTbMapper {

//    @Insert("insert into song_list_tb(song_list_name,song_list_intro," +
//            "user_id,label,song_list_img)" +
//            " values(#{songListName,jdbcType=VARCHAR},#{songListIntro,jdbcType=VARCHAR}," +
//            "#{userId,jdbcType=INTEGER},#{label,jdbcType=VARCHAR}," +
//            "#{songListImg,jdbcType=VARCHAR})")
//    int insertOne(SongListTb songListTb);

//    @Update("update song_list_tb set " +
//            "song_list_id=#{songListId},song_list_name=#{songListName}," +
//            "song_list_intro=#{songListIntro}," +
//            "user_id=#{userId},label=#{label}," +
//            "song_list_img=#{songListImg},create_time=#{createTime}," +
//            "update_time=#{updateTime},comment_number=#{commentNumber}," +
//            "play_number=#{playNumber},share_number=#{shareNumber}," +
//            "collect_number=#{collectNumber} " +
//            "where song_list_id=#{songListId}")
//    int updateOne(SongListTb songListTb);

    int updateOne(SongListTb songListTb);




    List<SongListTb> findSortCreateTime();

    SongListTb findBySongListId(Integer songListId);

    List<SongListTb> findByUserId(Integer userId);

    List<SongListTb> findSortCommentNumber();

    List<SongListTb> findSortPlayNumber();

    List<SongListTb> findSortShareNumber();

    List<SongListTb> findSortCollectNumber();

    List<SongListTb> findByLabel(String label);

    List<SongListTb> findByLabelSort(String label);

    Integer insertOne(SongListTb songListTb);

//    @Delete("delete from song_list_tb where song_list_id=#{songListId}")
//    int deleteById(Integer id);

    int deleteBySongListId(Integer songListId);

    List<SongListTb> searchBySongListName(String content);

    List<SongListTb> findAll();

}
