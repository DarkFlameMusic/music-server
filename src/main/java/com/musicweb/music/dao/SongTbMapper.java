package com.musicweb.music.dao;

import com.musicweb.music.entity.SongTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SongTbMapper {

    @Insert("insert into song_tb(singer_id,song_name,album_id," +
            "song_time,sing_style,language,lyric) " +
            "values(#{singerId,jdbcType=INTEGER},#{songName,jdbcType=VARCHAR}," +
            "#{albumId,jdbcType=INTEGER},#{songTime,jdbcType=INTEGER}," +
            "#{singStyle,jdbcType=VARCHAR},#{language,jdbcType=VARCHAR}," +
            "#{lyric,jdbcType=VARCHAR})")
    int insertOne(SongTb songTb);



    SongTb findById(Integer songId);

    List<SongTb> findSortPlayNumber();

//    @Update("update song_tb set " +
//            "song_id=#{songId},singer_id=#{singerId}," +
//            "song_name=#{songName},album_id=#{albumId}," +
//            "song_time=#{songTime},sing_style=#{singStyle}," +
//            "language=#{language},comment_number=#{commentNumber}," +
//            "play_number=#{playNumber},create_time=#{createTime}," +
//            "lyric=#{lyric} where song_id=#{songId}")
//    int updateOne(SongTb songTb);

    @Delete("delete from song_tb where song_id=#{songId}")
    int deleteById(Integer id);

    List<SongTb> searchBySongName(String content);

    List<SongTb> searchByLyric(String content);

    List<SongTb> findByAlbumId(Integer albumId);

    List<SongTb> findSortCreateTime();

    List<SongTb> findBySingerId(Integer singerId);

    List<SongTb> findAll();

    int updateOne(SongTb songTb);

}
