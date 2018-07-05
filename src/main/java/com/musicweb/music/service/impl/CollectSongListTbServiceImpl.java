package com.musicweb.music.service.impl;

import com.musicweb.music.dao.CollectSongListTbMapper;
import com.musicweb.music.entity.collecttable.CollectSongListTb;
import com.musicweb.music.service.CollectSongListTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectSongListTbServiceImpl implements CollectSongListTbService {

    @Autowired
    private CollectSongListTbMapper mapper;

    @Override
    public List<CollectSongListTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<CollectSongListTb> findBySongListId(Integer songListId) {
        return mapper.findBySongListId(songListId);
    }

    @Override
    public Integer insertOne(CollectSongListTb collectSongListTb) {
        return mapper.insertOne(collectSongListTb);
    }

    @Override
    public Integer deleteOne(Integer collectSongListId) {
        return mapper.deleteOne(collectSongListId);
    }

    @Override
    public CollectSongListTb findBySongListIdAndUserId(Integer songListId, Integer userId) {
        return mapper.findBySongListIdAndUserId(songListId,userId);
    }
}
