package com.musicweb.music.service.impl;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.AlbumTb;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumTbServiceImplTest {

    @Autowired
    private AlbumTbServiceImpl albumTbService;
    @Test
    public void findSortIssueTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-1);
        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findSortIssueTime(calendar.getTime(),1,35);
        Assert.assertNotNull(albumTbPageInfo);
    }

}