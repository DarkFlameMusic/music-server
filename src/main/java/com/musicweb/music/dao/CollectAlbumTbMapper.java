package com.musicweb.music.dao;

import com.musicweb.music.entity.collecttable.CollectAlbumTb;

import java.util.List;

public interface CollectAlbumTbMapper {

    List<CollectAlbumTb> findByUserId(Integer userId);

    List<CollectAlbumTb> findByAlbumId(Integer albumId);

    CollectAlbumTb findByUserIdAndAlbumId(Integer userId,Integer albumId);

    int insertOne(CollectAlbumTb collectAlbumTb);

    int deleteOne(Integer userId, Integer albumId);
}
