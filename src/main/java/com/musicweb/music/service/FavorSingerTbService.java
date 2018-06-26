package com.musicweb.music.service;

import com.musicweb.music.entity.favortable.FavorSingerTb;

import java.util.List;

public interface FavorSingerTbService {

    List<FavorSingerTb> findByUserId(Integer userId);

    List<FavorSingerTb> findBySingerId(Integer singerId);

    List<FavorSingerTb> findAllByUserId();

    List<FavorSingerTb> findAllBySingerId();

    Integer insertOne(FavorSingerTb favorSingerTb);

    Integer deleteOne(Integer userId,Integer singerId);
}
