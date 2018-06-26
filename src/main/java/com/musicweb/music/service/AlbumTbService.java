package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.AlbumTb;

import java.util.Date;
import java.util.List;

public interface AlbumTbService {

    PageInfo<AlbumTb> findBySingerIdSortIssueTime(Integer singerId,Integer pageNumber, Integer pageSize);

    AlbumTb findByAlbumId(Integer albumId);

    PageInfo<AlbumTb> searchByAlbumName(String content, Integer pageNumber, Integer pageSize);

    List<AlbumTb> findByCompanyName(String name);

    List<AlbumTb> findBySingerId(Integer singerId);

    List<AlbumTb> findSortPlayNumberAndIssueTime();

    PageInfo<AlbumTb> findSortIssueTime(Date issueTime, Integer pageNumber, Integer pageSize);

    PageInfo<AlbumTb> findAll(Integer pageNumber, Integer pageSize);

    Integer updateOne(AlbumTb albumTb);
}
