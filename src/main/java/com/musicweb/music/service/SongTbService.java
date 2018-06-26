package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.SongTb;

import java.util.List;

public interface SongTbService {

    List<SongTb> findSortPlayNumber();

    SongTb findBySongId(Integer songId);

    PageInfo<SongTb> searchBySongName(String content, Integer pageNumber, Integer pageSize);

    PageInfo<SongTb> searchByLyric(String content, Integer pageNumber, Integer pageSize);

    List<SongTb> findByAlbumId(Integer albumId);

    List<SongTb> findSortCreateTime();

    PageInfo<SongTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize);

    PageInfo<SongTb> findAll(Integer pageNumber, Integer pageSize);
}
