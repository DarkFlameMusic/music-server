package com.musicweb.music.dao;

import com.musicweb.music.entity.collecttable.CollectSongListTb;

import java.util.List;

public interface CollectSongListTbMapper {

    List<CollectSongListTb> findByUserId(Integer userId);

    List<CollectSongListTb> findBySongListId(Integer songListId);

    int insertOne(CollectSongListTb collectSongListTb);

    int deleteOne(Integer collectSongListId);
}
