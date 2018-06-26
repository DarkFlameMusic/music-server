package com.musicweb.music.dao;

import com.musicweb.music.entity.commenttable.SongListCommentTb;

import java.util.List;

public interface SongListCommentTbMapper {

    List<SongListCommentTb> findBySongListId(Integer songListId);

    List<SongListCommentTb> findByUserId(Integer userId);

    List<SongListCommentTb> findSortAdmireNumber();

    int insertOne(SongListCommentTb songListCommentTb);
}
