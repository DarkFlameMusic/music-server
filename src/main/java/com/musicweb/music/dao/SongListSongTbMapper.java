package com.musicweb.music.dao;

import com.musicweb.music.entity.SongListSongTb;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SongListSongTbMapper {

    int insertOne(SongListSongTb songListSongTb);

    @Select("select * from song_list_song_tb where song_list_song_id=#{songListSongId}")
    @Results({
            @Result(column = "song_list_song_id", property = "songListSongId"),
            @Result(column = "song_list_id", property = "songListId"),
            @Result(column = "song_id", property = "songId"),
            @Result(column = "create_time", property = "createTime")
    })
    SongListSongTb findById(Integer songListSongId);

    List<SongListSongTb> findBySongListId(Integer songListId);

    List<SongListSongTb> findBySongId(Integer songId);


    int updateOne(SongListSongTb songListSongTb);

    //    @Delete("delete from song_list_song_tb where song_list_song_id=#{songListSongId}")
//    int deleteById(Integer id);
    int deleteBySongListSongId(Integer songListSongId);


    int insertList(List<SongListSongTb> songListSongTbList);


}
