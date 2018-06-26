package com.musicweb.music.service.impl;

import com.musicweb.music.entity.UserTb;
import com.musicweb.music.enums.GenderEnum;
import com.musicweb.music.enums.UserJurisdictionEnum;
import com.musicweb.music.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTbServiceImplTest {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Test
    public void findById() throws Exception {
        UserTb userTb = userTbService.findById(12);
        Assert.assertNotNull(userTb);
    }

    @Test
    public void findByUsername() throws Exception {
    }

    @Test
    public void findByNickname() throws Exception {
        List<UserTb> list = userTbService.findByNickname("冰源");
        int result = list.size();
        Assert.assertEquals(2,result);
    }

    @Test
    public void findAllUser() throws Exception {
        List<UserTb> list = userTbService.findAll();
        int result = list.size();
        Assert.assertEquals(1,result);
    }

    @Test
    public void findAllSinger() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void insertForSignIn() throws Exception {
        UserTb userTb = new UserTb();
        userTb.setUsername("8584966@qq.com");
        userTb.setPassword(MD5Util.encode("45646"));
        userTb.setUserNickname("dsad");
        //默认属性
        userTb.setMail(userTb.getUsername());
        userTb.setJurisdiction(UserJurisdictionEnum.WAIT.getCode());
        userTb.setGender(GenderEnum.UNKNOWN_GENDER.getCode());
        userTbService.insertForSignIn(userTb);

    }

    @Test
    public void compilePersonalData() throws Exception {

    }

}