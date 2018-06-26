package com.musicweb.music.service;

import com.musicweb.music.entity.collecttable.CollectSongListTb;

import java.util.List;

public interface CollectSongListTbService {

    List<CollectSongListTb> findByUserId(Integer userId);

    List<CollectSongListTb> findBySongListId(Integer songListId);

    Integer insertOne(CollectSongListTb collectSongListTb);

    Integer deleteOne(Integer collectSongListId);
}
