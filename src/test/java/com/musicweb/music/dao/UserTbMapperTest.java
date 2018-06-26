package com.musicweb.music.dao;

import com.musicweb.music.entity.UserTb;
import org.apache.ibatis.annotations.Insert;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTbMapperTest {
    @Autowired
    private UserTbMapper mapper;
    @Test
    public void insertByObject() throws Exception {
        UserTb userTb = new UserTb();
        userTb.setUsername("789@gay.com");
        userTb.setPassword("123456");
        userTb.setUserNickname("冰源");
        userTb.setMail(userTb.getUsername());
        userTb.setJurisdiction(3);
        userTb.setCaptcha("456");
        int result = mapper.insertForSignIn(userTb);
        Assert.assertEquals(1,result);
    }
    @Test
    public void updateByUserId() throws Exception{
        UserTb usera = mapper.findByUserId(1);
//        UserTb userTb = new UserTb();
//        userTb.setUserId(usera.getUserId());
//        userTb.setUsername(usera.getUsername());
//        userTb.setPassword(usera.getPassword());
//        userTb.setMail(usera.getMail());
//        userTb.setJurisdiction(usera.getJurisdiction());
//        userTb.setPhoneNumber(usera.getPhoneNumber());
//        userTb.setHeadImg(usera.getHeadImg());
//        userTb.setBirthDate(usera.getBirthDate());
//        userTb.setPersonIntro(usera.getPersonIntro());
//        userTb.setAttentionNumber(usera.getAttentionNumber());
//        userTb.setFanNumber(usera.getFanNumber());
//        userTb.setCreateTime(usera.getCreateTime());
//        userTb.setArea(usera.getArea());

        usera.setUserNickname("zcz");
        usera.setUpdateTime(new Date());

        int result = mapper.updateByUserId(usera);
        Assert.assertEquals(1,result);
    }
    @Test
    public void findByUserId(){
        UserTb result = mapper.findByUserId(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById(){
        int result = mapper.deleteById(11);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByUsername(){
        UserTb result=mapper.findByUsername("ghj@gay.com");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByUserNickname(){
        List<UserTb> list = mapper.findByUserNickname("冰源");
        int result = list.size();
        Assert.assertEquals(2,result);
    }

    @Test
    public void findByJurisdiction(){
        List<UserTb> list = mapper.findByJurisdiction(0);
        int result = list.size();
        Assert.assertEquals(3,result);
    }

}