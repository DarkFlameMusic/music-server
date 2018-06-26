package com.musicweb.music.service;

import com.musicweb.music.entity.commenttable.SongListCommentTb;

import java.util.List;

public interface SongListCommentTbService {

    List<SongListCommentTb> findBySongListId(Integer songListId);

    List<SongListCommentTb> findByUserId(Integer userId);

    List<SongListCommentTb> findSortAdmireNumber();

    Integer insertOne(SongListCommentTb songListCommentTb);
}
