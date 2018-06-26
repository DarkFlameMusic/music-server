package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.MvTbMapper;
import com.musicweb.music.entity.MvTb;
import com.musicweb.music.service.MvTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MvTbServiceImpl implements MvTbService{

    @Autowired
    private MvTbMapper mapper;

    @Override
    public PageInfo<MvTb> searchByMvName(String content, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.searchByMvName(content));
    }

    @Override
    public PageInfo<MvTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize) {
        return new PageInfo<>(mapper.findBySingerId(singerId));
    }
}
