package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.utils.IntegerUtil;

import java.util.List;

public interface SongListTbService {

    List<SongListTb> findAllSortCommentNumber();

    List<SongListTb> findAllSortPlayNumber();

    List<SongListTb> findAllSortShareNumber();

    List<SongListTb> findAllSortCollectNumber();

    List<SongListTb> findByLabel(String label);

    PageInfo<SongListTb> searchByLabel(String content, Integer pageNumber,Integer pageSize,String order);

    PageInfo<SongListTb> searchByLabel(Integer pageNumber,Integer pageSize,String order);

    List<SongListTb> findByUserId(Integer userId);

    SongListTb findBySongListId(Integer songListId);

    Integer insertOne(SongListTb songListTb);

    Integer updateOne(SongListTb songListTb);

    Integer deleteOne(Integer songListId);

    PageInfo<SongListTb> searchBySongListName(String content, Integer pageNumber, Integer pageSize);

    PageInfo<SongListTb> findAll(Integer pageNumber, Integer pageSize);
}
