package com.musicweb.music.dao;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.CommentTb;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentTbMapper {

    int insertOne(CommentTb commentTb);

    int deleteOne(Integer commentId);

    List<CommentTb> findByObjectId(Integer objectId,Integer objectType);

    List<CommentTb> findByPage(Integer objectId, Integer objectType);

    List<CommentTb> findSortAdmireNumber(Integer objectId, Integer objectType,Integer minAdmireNumber);
}
