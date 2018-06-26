package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.SingerTbMapper;
import com.musicweb.music.entity.SingerTb;
import com.musicweb.music.service.SingerTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SingerTbServiceImpl implements SingerTbService{

    @Autowired
    private SingerTbMapper mapper;

    @Override
    public SingerTb findBySingerId(Integer singerId) {
        return mapper.findById(singerId);
    }

    @Override
    public List<SingerTb> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<SingerTb> findAllInSinger() {
        return mapper.findAllInSinger();
    }

    @Override
    public List<SingerTb> findByType(Integer singerType) {
        return mapper.findByType(singerType);
    }

    @Override
    public List<SingerTb> findByTypeAndInitial(Integer singerType, String initial) {
        return mapper.findByTypeAndInitial(singerType,initial);
    }

    @Override
    public PageInfo<SingerTb> searchBySingerName(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<SingerTb>(mapper.searchBySingerName(content));
    }

    @Override
    public SingerTb findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    public PageInfo<SingerTb> findAllPage(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }
}
