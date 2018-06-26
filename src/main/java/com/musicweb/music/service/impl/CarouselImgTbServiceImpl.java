package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.CarouselImgTbMapper;
import com.musicweb.music.entity.CarouselImgTb;
import com.musicweb.music.service.CarouselImgTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarouselImgTbServiceImpl implements CarouselImgTbService{

    @Autowired
    private CarouselImgTbMapper mapper;


    @Override
    public PageInfo<CarouselImgTb> findAll(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.findAll());
    }

    @Override
    public Integer insertOne(CarouselImgTb carouselImgTb) {
        return mapper.insertOne(carouselImgTb);
    }

    @Override
    public Integer deleteOne(Integer carouselId) {
        return mapper.deleteById(carouselId);
    }

    @Override
    public PageInfo<CarouselImgTb> searchByLinkUrl(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.searchByLinkUrl(content));
    }
}
