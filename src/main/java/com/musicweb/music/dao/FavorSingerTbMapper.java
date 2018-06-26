package com.musicweb.music.dao;

import com.musicweb.music.entity.favortable.FavorSingerTb;

import java.util.List;

public interface FavorSingerTbMapper {

   List<FavorSingerTb> findByUserId(Integer userId);

   List<FavorSingerTb> findBySingerId(Integer singerId);

   List<FavorSingerTb> findAllByUserId();

   List<FavorSingerTb> findAllBySingerId();

   int insertOne(FavorSingerTb favorSingerTb);

   int deleteOne(Integer userId,Integer singerId);

}
