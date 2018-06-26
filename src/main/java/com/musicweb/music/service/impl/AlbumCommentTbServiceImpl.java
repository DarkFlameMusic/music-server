package com.musicweb.music.service.impl;

import com.musicweb.music.dao.AlbumCommentTbMapper;
import com.musicweb.music.entity.commenttable.AlbumCommentTb;
import com.musicweb.music.service.AlbumCommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlbumCommentTbServiceImpl implements AlbumCommentTbService{

    @Autowired
    private AlbumCommentTbMapper mapper;

    @Override
    public Integer insertOne(AlbumCommentTb albumCommentTb) {
        return mapper.insertOne(albumCommentTb);
    }
}
