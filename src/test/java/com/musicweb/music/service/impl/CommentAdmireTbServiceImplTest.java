package com.musicweb.music.service.impl;

import com.musicweb.music.entity.CommentAdmireTb;
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
public class CommentAdmireTbServiceImplTest {

    @Autowired
    private CommentAdmireTbServiceImpl commentAdmireTbService;
    @Test
    public void insertOne() throws Exception {
        CommentAdmireTb commentAdmireTb = new CommentAdmireTb();
        commentAdmireTb.setUserId(123);
        commentAdmireTb.setCommentId(456);
        commentAdmireTb.setCommentType(1);
        commentAdmireTb.setCreateTime(new Date());
        int result = commentAdmireTbService.insertOne(commentAdmireTb);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteById() throws Exception {
        int result = commentAdmireTbService.deleteById(1);
        Assert.assertEquals(1,result);
    }

}