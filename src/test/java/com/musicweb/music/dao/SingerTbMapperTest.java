package com.musicweb.music.dao;

import com.musicweb.music.entity.SingerTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SingerTbMapperTest {

    @Autowired
    private SingerTbMapper mapper;


    @Test
    public void insertOne() throws Exception {
        SingerTb singerTb = new SingerTb();
        singerTb.setSingerName("吴超最丑");
        singerTb.setSingerImg("xxxx.jpg");
        singerTb.setSingerOneIntro("是个gay");
        singerTb.setSingerIntro("gay");
        int result = mapper.insertOne(singerTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findById() throws Exception {
        SingerTb result = mapper.findById(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateOne() throws Exception {
        SingerTb singerTb = mapper.findById(1);

        singerTb.setSingerName("吴超");
        int result = mapper.updateOne(singerTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteById() throws Exception {
        int result = mapper.deleteById(1);
        Assert.assertEquals(1,result);
    }

}