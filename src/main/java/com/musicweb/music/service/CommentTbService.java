package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.CommentTb;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentTbService {

    Integer insertOne(CommentTb commentTb);

    Integer deleteOne(Integer commentId);

    List<CommentTb> findByObjectId(Integer objectId, Integer objectType);

    PageInfo<CommentTb> findByPage(Integer objectId, Integer objectType, Integer pageNumber, Integer pageSize);

    List<CommentTb> findSortAdmireNumber(Integer objectId, Integer objectType,Integer minAdmireNumber);

}
