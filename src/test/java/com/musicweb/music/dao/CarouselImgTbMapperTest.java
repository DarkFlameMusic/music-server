package com.musicweb.music.dao;

import com.musicweb.music.entity.CarouselImgTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarouselImgTbMapperTest {

    @Autowired
    private CarouselImgTbMapper mapper;
    @Test
    public void updateTestOne() throws Exception {
        CarouselImgTb carouselImgTba = mapper.findById(1);
//        CarouselImgTb carouselImgTb = new CarouselImgTb();
//        carouselImgTb.setCarouselImgId(carouselImgTba.getCarouselImgId());
//        carouselImgTb.setCarouselImg(carouselImgTba.getCarouselImg());
//        carouselImgTb.setCarouselUrl("aaaaaaaaaaa");
//        carouselImgTb.setCreateTime(carouselImgTba.getCreateTime());
        carouselImgTba.setCarouselUrl("xxxxxxxxxxxx");
        int result = mapper.updateTestOne(carouselImgTba);
        Assert.assertEquals(1,result);

    }

    @Test
    public void insertOne(){
        CarouselImgTb carouselImgTb = new CarouselImgTb();
        carouselImgTb.setCarouselImg("xxxx.jpg");
        carouselImgTb.setCarouselUrl("xxxx.com");
//        int result = mapper.insertOne(carouselImgTb);
//        Assert.assertEquals(1,result);
    }

}