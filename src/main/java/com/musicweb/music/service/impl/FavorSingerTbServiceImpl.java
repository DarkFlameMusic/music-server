package com.musicweb.music.service.impl;

import com.musicweb.music.dao.FavorSingerTbMapper;
import com.musicweb.music.dao.SingerTbMapper;
import com.musicweb.music.entity.SingerTb;
import com.musicweb.music.entity.favortable.FavorSingerTb;
import com.musicweb.music.service.FavorSingerTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FavorSingerTbServiceImpl implements FavorSingerTbService{

    @Autowired
    private FavorSingerTbMapper mapper;

    @Autowired
    private SingerTbMapper singerTbMapper;

    @Override
    public List<FavorSingerTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public List<FavorSingerTb> findBySingerId(Integer singerId) {
        return mapper.findBySingerId(singerId);
    }

    @Override
    public List<FavorSingerTb> findAllByUserId() {
        return mapper.findAllByUserId();
    }

    @Override
    public List<FavorSingerTb> findAllBySingerId() {
        return mapper.findAllBySingerId();
    }

    @Override
    @Transactional
    public Integer insertOne(FavorSingerTb favorSingerTb) {
        Integer result = mapper.insertOne(favorSingerTb);
        SingerTb singerTb = singerTbMapper.findById(favorSingerTb.getSingerId());
        if (singerTb.getPopularity() == null){
            singerTb.setPopularity(1);
        }else{
            singerTb.setPopularity(singerTb.getPopularity()+1);
        }
        return result;
    }

    @Override
    @Transactional
    public Integer deleteOne(Integer userId, Integer singerId) {
        Integer result = mapper.deleteOne(userId,singerId);
        SingerTb singerTb = singerTbMapper.findById(singerId);
        Integer n = singerTb.getPopularity();
        n--;
        singerTb.setPopularity(n);
        return result;
    }
}
