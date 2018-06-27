package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.AlbumTbVO;
import com.musicweb.music.VO.SongTbVO;
import com.musicweb.music.dao.AlbumTbMapper;
import com.musicweb.music.entity.AlbumTb;
import com.musicweb.music.entity.SongTb;
import com.musicweb.music.service.AlbumTbService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class AlbumTbServiceImpl implements AlbumTbService{

    @Autowired
    private AlbumTbMapper mapper;

    @Autowired
    private SongTbServiceImpl songTbService;

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

    @Override
    public Integer updateOne(AlbumTb albumTb) {
        return mapper.updateOne(albumTb);
    }

    public Integer deleteOne(Integer albumId){
        List<SongTb> songTbs = songTbService.findByAlbumId(albumId);
        for (SongTb songTb : songTbs) {
            songTbService.deleteOne(songTb.getSongId());
        }
        return mapper.deleteById(albumId);
    }

    public Integer insertOne(AlbumTb albumTb){return  mapper.insertOne(albumTb);}

    public AlbumTbVO getAlbumTbVObyAlbumId(Integer albumId){
        AlbumTbVO albumTbVO = new AlbumTbVO();
        AlbumTb albumTb = this.findByAlbumId(albumId);
        BeanUtils.copyProperties(albumTb,albumTbVO);
        List<SongTb> songTbs = songTbService.findByAlbumId(albumTb.getAlbumId());
        List<SongTbVO> songTbVOS = new ArrayList<>();
        for (SongTb songTb : songTbs) {
            SongTbVO songTbVO = songTbService.getSongTbVoBySongTb(songTb);
            songTbVOS.add(songTbVO);
        }
        albumTbVO.setSongs(songTbVOS);
        return albumTbVO;
    }
}
