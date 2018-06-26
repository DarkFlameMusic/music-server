package com.musicweb.music.service;

import com.musicweb.music.entity.CommentAdmireTb;

import java.util.List;

public interface CommentAdmireTbService {

    Integer insertOne(CommentAdmireTb commentAdmireTb);

    Integer deleteById(Integer commentAdmireId);

    List<CommentAdmireTb> findBycCommentType(Integer commentType);
}
