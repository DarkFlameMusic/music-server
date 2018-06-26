package com.musicweb.music.service.impl;

import com.musicweb.music.dao.CommentAdmireTbMapper;
import com.musicweb.music.entity.CommentAdmireTb;
import com.musicweb.music.service.CommentAdmireTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentAdmireTbServiceImpl implements CommentAdmireTbService{

    @Autowired
    private CommentAdmireTbMapper mapper;

    @Override
    public Integer insertOne(CommentAdmireTb commentAdmireTb) {
        return mapper.insertOne(commentAdmireTb);
    }

    @Override
    public Integer deleteById(Integer commentAdmireId) {
        return mapper.deleteById(commentAdmireId);
    }

    @Override
    public List<CommentAdmireTb> findBycCommentType(Integer commentType) {
        return mapper.findByCommentType(commentType);
    }
}
