package com.musicweb.music.dao;

import com.musicweb.music.entity.AlbumTb;
import org.apache.ibatis.annotations.Delete;

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

    Integer insertOne(AlbumTb albumTb);

    int updateOne(AlbumTb albumTb);

    @Delete("delete from album_tb where album_id = #{id}")
    int deleteById(Integer id);

}
