package com.musicweb.music.dao;

import com.musicweb.music.entity.SongListSongTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SongListSongTbMapperTest {

    @Autowired
    private SongListSongTbMapper mapper;
    @Test
    public void insertOne() throws Exception {
        SongListSongTb songListSongTb = new SongListSongTb();
        songListSongTb.setSongListId(1);
        songListSongTb.setSongId(1);
        int result = mapper.insertOne(songListSongTb);
        Assert.assertEquals(1,result);

    }

    @Test
    public void findById() throws Exception {
        SongListSongTb result = mapper.findById(1);
        Assert.assertNotNull(result);

    }

    @Test
    public void updateOne() throws Exception {

        SongListSongTb songListSongTb = mapper.findById(1);

        songListSongTb.setSongId(3);
        int result = mapper.updateOne(songListSongTb);
        Assert.assertEquals(1,result);
    }

}