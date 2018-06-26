package com.musicweb.music.service.impl;

import com.musicweb.music.dao.SongCommentTbMapper;
import com.musicweb.music.entity.commenttable.SongCommentTb;
import com.musicweb.music.service.SongCommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SongCommentTbServiceImpl implements SongCommentTbService{

    @Autowired
    private SongCommentTbMapper mapper;

    @Override
    public Integer insertOne(SongCommentTb songCommentTb) {
        return mapper.insertOne(songCommentTb);
    }
}
