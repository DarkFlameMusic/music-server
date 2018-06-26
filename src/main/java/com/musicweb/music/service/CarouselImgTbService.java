package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.CarouselImgTb;

import java.util.List;

public interface CarouselImgTbService {

    PageInfo<CarouselImgTb> findAll(Integer pageNumber, Integer pageSize);

    Integer insertOne(CarouselImgTb carouselImgTb);

    Integer deleteOne(Integer carouselId);

    PageInfo<CarouselImgTb> searchByLinkUrl(String content,Integer pageNumber, Integer pageSize);
}
