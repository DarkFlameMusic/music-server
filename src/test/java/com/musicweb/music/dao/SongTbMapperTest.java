package com.musicweb.music.dao;

import com.musicweb.music.entity.SongTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SongTbMapperTest {

    @Autowired
    private SongTbMapper mapper;
    @Test
    public void insertOne() throws Exception {
        SongTb songTb = new SongTb();
        songTb.setSingerId(1);
        songTb.setSongName("不知道");
        songTb.setAlbumId(1);
        songTb.setSongTime(456);
        songTb.setSingStyle("爵士");
        songTb.setLanguage("英语");
        songTb.setLyric("大大飒飒的打算");

        int result = mapper.insertOne(songTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findById() throws Exception {
        SongTb result = mapper.findById(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateOne() throws Exception {
        SongTb songTb = mapper.findById(1);
        songTb.setCommentNumber(1);
        songTb.setPlayNumber(1);

        int result = mapper.updateOne(songTb);
        Assert.assertEquals(1,result);

    }

}