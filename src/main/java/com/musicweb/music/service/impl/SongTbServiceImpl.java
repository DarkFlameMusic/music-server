package com.musicweb.music.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.SongTbMapper;
import com.musicweb.music.entity.SongTb;
import com.musicweb.music.service.SongTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongTbServiceImpl implements SongTbService {

    @Autowired
    private SongTbMapper mapper;
    @Override
    public List<SongTb> findSortPlayNumber() {
        return mapper.findSortPlayNumber();
    }

    @Override
    public SongTb findBySongId(Integer songId) {
        return mapper.findById(songId);
    }

    @Override
    public PageInfo<SongTb> searchBySongName(String content, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<SongTb>(mapper.searchBySongName(content));
    }

    @Override
    public PageInfo<SongTb> searchByLyric(String content, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<SongTb>(mapper.searchByLyric(content));
    }

    @Override
    public List<SongTb> findByAlbumId(Integer albumId) {
        return mapper.findByAlbumId(albumId);
    }

    @Override
    public List<SongTb> findSortCreateTime() {
        return mapper.findSortCreateTime();
    }

    @Override
    public PageInfo<SongTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);

        return new PageInfo<>(mapper.findBySingerId(singerId));
    }

    @Override
    public PageInfo<SongTb> findAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }
}
