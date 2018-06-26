package com.musicweb.music.dao;

import com.musicweb.music.entity.CommentAdmireTb;

import java.util.List;

public interface CommentAdmireTbMapper {

    int insertOne(CommentAdmireTb commentAdmireTb);

    int deleteById(Integer commentAdmireId);

    List<CommentAdmireTb> findByCommentType(Integer commentType);
}
