package com.musicweb.music.dao;

import com.musicweb.music.entity.SongListTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SongListTbMapperTest {

    @Autowired
    private SongListTbMapper mapper;
    @Test
    public void insertOne() throws Exception {
        SongListTb songListTb = new SongListTb();
        songListTb.setSongListName("360°沦陷 | 极致诱惑的一百款日系男声");
        songListTb.setSongListIntro("gay取的名字");
        songListTb.setUserId(11);
        songListTb.setLabel("爵士");
        songListTb.setSongListImg("http://p1.music.126.net/PH84DCJr7IdUwrJvue49Rw==/18872017579728048.jpg?param=140y140");
        int result = mapper.insertOne(songListTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateOne(){
        SongListTb songListTb = mapper.findBySongListId(1);

        songListTb.setCommentNumber(99);
        songListTb.setPlayNumber(99);
        songListTb.setShareNumber(99);
        songListTb.setCollectNumber(99);
        int result = mapper.updateOne(songListTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByUserId(){
        SongListTb result = null;
        Assert.assertNotNull(result);
    }

}