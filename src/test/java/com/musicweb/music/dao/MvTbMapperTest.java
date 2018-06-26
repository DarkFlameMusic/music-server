package com.musicweb.music.dao;

import com.musicweb.music.entity.MvTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MvTbMapperTest {

    @Autowired
    private MvTbMapper mapper;

    @Test
    public void insertOne() throws Exception {
        MvTb mvTb = new MvTb();
        mvTb.setCommentNumber(0);
        mvTb.setPlayNumber(0);
        mvTb.setShareNumber(0);
        mvTb.setCollectNumber(0);
        int result = mapper.insertOne(mvTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findById() throws Exception {
        MvTb result = mapper.findById(1);
        Assert.assertNotNull(result);

    }

    @Test
    public void updateOne() throws Exception {
        MvTb mvTb = mapper.findById(1);
        mvTb.setCollectNumber(1);
        int result = mapper.updateOne(mvTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteById() throws Exception {
        int result = mapper.deleteById(2);
        Assert.assertEquals(1,result);
    }

}