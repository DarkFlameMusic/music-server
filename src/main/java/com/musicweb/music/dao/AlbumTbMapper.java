package com.musicweb.music.dao;

import com.musicweb.music.entity.AlbumTb;

import java.util.Date;
import java.util.List;

public interface AlbumTbMapper {

    List<AlbumTb> findBySingerIdSortIssueTime(Integer singerId);

    AlbumTb findByAlbumId(Integer albumId);

    List<AlbumTb> searchByAlbumName(String content);

    List<AlbumTb> findByCompanyName(String name);

    List<AlbumTb> findBySingerId(Integer singerId);

    List<AlbumTb> findSortPlayNumberAndIssueTime();

    List<AlbumTb> findSortIssueTime(Date issueTime);

    List<AlbumTb> findAll();

    int updateOne(AlbumTb albumTb);
}
