package com.musicweb.music.service.impl;

import com.musicweb.music.entity.SongListSongTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SongListSongTbServiceImplTest {

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;
    @Test
    public void findBySongListId() throws Exception {
    }

    @Test
    public void insertOne() throws Exception {
    }

    @Test
    public void deleteBySongListSongId() throws Exception {
    }

    @Test
    public void findBySongId() throws Exception {
    }

    @Test
    public void insertList() throws Exception {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            SongListSongTb songListSongTb = new SongListSongTb();
//            songListSongTb.setSongId(i);
//            songListSongTb.setSongListId(123);
            list.add(i);
        }
        int result = songListSongTbService.insertList(123,list);
        Assert.assertNotNull(result);
    }

}