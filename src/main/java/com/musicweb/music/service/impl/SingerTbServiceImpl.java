package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.SingerTbMapper;
import com.musicweb.music.entity.AlbumTb;
import com.musicweb.music.entity.SingerTb;
import com.musicweb.music.service.AlbumTbService;
import com.musicweb.music.service.SingerTbService;
import com.musicweb.music.service.SongTbService;
import com.musicweb.music.utils.InitialUtil;
import io.swagger.annotations.ApiOperation;
import okhttp3.internal.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SingerTbServiceImpl implements SingerTbService{

    @Autowired
    private SingerTbMapper mapper;

    @Autowired
    private InitialUtil initialUtil;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

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

    public Integer deleteById(Integer id){
        List<AlbumTb> albumTbs = albumTbService.findBySingerId(id);
        for (AlbumTb albumTb : albumTbs) {
            albumTbService.deleteOne(albumTb.getAlbumId());
        }
        return mapper.deleteById(id);
    }

    public SingerTb updateSinger(SingerTb singerTb){
        singerTb.setInitial(String.valueOf(initialUtil.getStringPinyin(singerTb.getSingerName()).toUpperCase().charAt(0)));
        mapper.updateOne(singerTb);
        return findBySingerId(singerTb.getSingerId());
    }

    public Integer createSinger(SingerTb singerTb){
        return mapper.insertOne(singerTb);
    }
}
