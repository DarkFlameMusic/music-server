package com.musicweb.music.service.impl;

import com.musicweb.music.entity.CarouselImgTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarouselImgTbServiceImplTest {

    @Autowired
    private CarouselImgTbServiceImpl carouselImgTbService;
    @Test
    public void findAll() throws Exception {
//        List<CarouselImgTb> list = carouselImgTbService.findAll();
//        int result = list.size();
//        Assert.assertEquals(3,result);
    }

    @Test
    public void insertOne() throws Exception {

        CarouselImgTb carouselImgTb = new CarouselImgTb();
        carouselImgTb.setCarouselImg("dsa");
        carouselImgTb.setCarouselUrl("dsdas");
        carouselImgTb.setCreateTime(new Date());
        Assert.assertNotNull(carouselImgTbService.insertOne(carouselImgTb));
    }

}