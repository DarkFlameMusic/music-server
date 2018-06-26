package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.UserTb;

import java.util.List;

public interface UserTbService {
    UserTb findById(Integer id);

    UserTb findByUsername(String username);

    List<UserTb> findByNickname(String userNickname);

    List<UserTb> findAllAdmin();

    PageInfo<UserTb> findAllUser(Integer pageNumber,Integer pageSize);

    PageInfo<UserTb> findAllSinger(Integer pageNumber,Integer pageSize);

    List<UserTb> findAll();
    //插入
    Integer insertForSignIn(UserTb userTb);
    //编辑个人资料
    Integer compilePersonalData(UserTb userTb);

    UserTb findByCaptcha(String captcha);

    Integer deleteById(Integer userId);

    PageInfo<UserTb> searchByUserNickname(String content, Integer pageNumber, Integer pageSize);

}
