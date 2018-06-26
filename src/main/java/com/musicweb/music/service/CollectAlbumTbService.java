package com.musicweb.music.service;

import com.musicweb.music.entity.collecttable.CollectAlbumTb;

import java.util.List;

public interface CollectAlbumTbService {

    List<CollectAlbumTb> findByUserId(Integer userId);

    List<CollectAlbumTb> findByAlbumId(Integer albumId);

    CollectAlbumTb findByUserIdAndAlbumId(Integer userId,Integer albumId);

    Integer insertOne(CollectAlbumTb collectAlbumTb);

    Integer deleteOne(Integer userId, Integer albumId);
}
