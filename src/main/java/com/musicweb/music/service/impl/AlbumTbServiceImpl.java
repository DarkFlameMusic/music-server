package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.AlbumTbMapper;
import com.musicweb.music.entity.AlbumTb;
import com.musicweb.music.service.AlbumTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AlbumTbServiceImpl implements AlbumTbService{

    @Autowired
    private AlbumTbMapper mapper;

    @Override
    public PageInfo<AlbumTb> findBySingerIdSortIssueTime(Integer singerId,Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findBySingerIdSortIssueTime(singerId));
    }

    @Override
    public AlbumTb findByAlbumId(Integer albumId) {
        return mapper.findByAlbumId(albumId);
    }

    @Override
    public PageInfo<AlbumTb> searchByAlbumName(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<AlbumTb>(mapper.searchByAlbumName(content));
    }

    @Override
    public List<AlbumTb> findByCompanyName(String name) {
        return mapper.findByCompanyName(name);
    }

    @Override
    public List<AlbumTb> findBySingerId(Integer singerId) {
        return mapper.findBySingerId(singerId);
    }


    @Override
    public List<AlbumTb> findSortPlayNumberAndIssueTime() {
        return mapper.findSortPlayNumberAndIssueTime();
    }

    @Override
    public PageInfo<AlbumTb> findSortIssueTime(Date issueTime, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);

        return new PageInfo<>(mapper.findSortIssueTime(issueTime));
    }

    @Override
    public PageInfo<AlbumTb> findAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }
}
