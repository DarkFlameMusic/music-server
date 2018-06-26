package com.musicweb.music.service.impl;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.CommentTb;
import com.musicweb.music.enums.CommentTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTbServiceImplTest {

    @Autowired
    private CommentTbServiceImpl commentTbService;
    @Test
    public void insertOne() throws Exception {
        CommentTb commentTb = new CommentTb();
        commentTb.setObjectId(1);
        commentTb.setUserId(1);
        commentTb.setObjectType(CommentTypeEnum.SONG_COMMENT.getCode());
        commentTb.setComment("456789");
        commentTb.setCreateTime(new Date());
        int result = commentTbService.insertOne(commentTb);

        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteOne() throws Exception {

        int result = commentTbService.deleteOne(2);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByObjectId() throws Exception {
        List<CommentTb> commentTbList = commentTbService.findByObjectId(1,CommentTypeEnum.SONG_COMMENT.getCode());
        Assert.assertNotNull(commentTbList);

    }

    /**
     * 页数小于1返回第一页数据
     * 页数超过总页数返回最后一页
     * @throws Exception
     */
    @Test
    public void findByPage() throws Exception{
        PageInfo<CommentTb> commentTbList = commentTbService.findByPage(1,0,1,10);

        Assert.assertNotNull(commentTbList);
    }

    @Test
    public void findSortAdmireNumber() throws Exception{
        List<CommentTb> commentTbList = commentTbService.findSortAdmireNumber(1,0,100);

        Assert.assertNotNull(commentTbList);
    }
}