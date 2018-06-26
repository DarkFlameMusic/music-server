package com.musicweb.music.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.dao.SongListTbMapper;
import com.musicweb.music.dao.UserTbMapper;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.service.UserTbService;
import com.musicweb.music.utils.VerifyUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musicweb.music.enums.UserJurisdictionEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class UserTbServiceImpl implements UserTbService{

    @Autowired
    private UserTbMapper mapper;

    @Autowired
    private SongListTbMapper songListTbMapper;

    private final String MYMUSIC = "我喜欢的音乐";

    @Override
    public UserTb findById(Integer id) {
        return mapper.findByUserId(id);
    }

    @Override
    public UserTb findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public List<UserTb> findByNickname(String userNickname) {
        return mapper.findByUserNickname(userNickname);
    }

    @Override
    public List<UserTb> findAllAdmin() {
        return mapper.findByJurisdiction(UserJurisdictionEnum.ADMIN.getCode());
    }

    @Override
    public PageInfo<UserTb> findAllUser(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        PageInfo<UserTb> userTbPageInfo = new PageInfo<>(mapper.findByJurisdiction(UserJurisdictionEnum.USER.getCode()));
        return userTbPageInfo;
    }

    @Override
    public PageInfo<UserTb> findAllSinger(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        PageInfo<UserTb> userTbPageInfo = new PageInfo<>(mapper.findByJurisdiction(UserJurisdictionEnum.SINGER.getCode()));
        return userTbPageInfo;
    }

    @Override
    public List<UserTb> findAll() {
        List<UserTb> listAll = mapper.findByJurisdiction(UserJurisdictionEnum.ADMIN.getCode());
        List<UserTb> listUser = mapper.findByJurisdiction(UserJurisdictionEnum.USER.getCode());
        List<UserTb> listSinger = mapper.findByJurisdiction(UserJurisdictionEnum.SINGER.getCode());
        listAll.addAll(listUser);
        listAll.addAll(listSinger);
        return listAll;
    }

    @Override
    @Transactional
    public Integer insertForSignIn(UserTb userTb) {

        userTb.setMail(userTb.getUsername());

        Integer result = mapper.insertForSignIn(userTb);
        VerifyUserUtil.verifyNotNull(userTb);
        SongListTb songListTb = new SongListTb();
        songListTb.setSongListName(MYMUSIC);
        songListTb.setSongListImg("default");
        songListTb.setCreateTime(new Date());
        songListTb.setUserId(userTb.getUserId());
        songListTb.setLabel("goubi,龚华健");
        songListTbMapper.insertOne(songListTb);
        return result;
    }

    @Override
    public Integer compilePersonalData(UserTb userTb) {
        return mapper.updateByUserId(userTb);
    }

    @Override
    public UserTb findByCaptcha(String captcha) {
        UserTb userTb = mapper.findByCaptcha(captcha);

        VerifyUserUtil.verifyCaptcha(userTb);

        return userTb;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer userId) {
        songListTbMapper.deleteBySongListNameAndUserId(MYMUSIC,userId);
        return mapper.deleteById(userId);
    }

    @Override
    public PageInfo<UserTb> searchByUserNickname(String content, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber,pageSize);
        return new PageInfo<>(mapper.searchByUserNickname(content));
    }
}
