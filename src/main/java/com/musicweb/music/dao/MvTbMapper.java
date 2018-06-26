package com.musicweb.music.dao;

import com.musicweb.music.entity.MvTb;

import java.util.List;

public interface MvTbMapper {

    int insertOne(MvTb mvTb);

    MvTb findById(Integer id);

    int updateOne(MvTb mvTb);

    int deleteById(Integer id);

    List<MvTb> searchByMvName(String content);

    List<MvTb> findBySingerId(Integer singerId);

}
