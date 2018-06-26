package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.CommentTbMapper;
import com.musicweb.music.entity.CommentTb;
import com.musicweb.music.service.CommentTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentTbServiceImpl implements CommentTbService{

    @Autowired
    private CommentTbMapper mapper;

    @Override
    public Integer insertOne(CommentTb commentTb) {
        return mapper.insertOne(commentTb);
    }

    @Override
    public Integer deleteOne(Integer commentId) {
        return mapper.deleteOne(commentId);
    }

    @Override
    public List<CommentTb> findByObjectId(Integer objectId, Integer objectType) {
        return mapper.findByObjectId(objectId,objectType);
    }

    @Override
    public PageInfo<CommentTb> findByPage(Integer objectId, Integer objectType, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findByPage(objectId,objectType));
    }

    @Override
    public List<CommentTb> findSortAdmireNumber(Integer objectId, Integer objectType,Integer minAdmireNumber) {
        List<CommentTb> commentTbList = mapper.findSortAdmireNumber(objectId,objectType,minAdmireNumber);
        if (commentTbList.size() == 0){
            return null;
        }else{
            return commentTbList;
        }

    }
}
