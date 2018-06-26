package com.musicweb.music.service;

import com.musicweb.music.entity.UserListenTb;

import java.util.List;

public interface UserListenTbService {

    List<UserListenTb> findSortPlayNumber(Integer userId);

    UserListenTb findByUserIdAndSongId(Integer userId, Integer songId);

    Integer insertOne(UserListenTb userListenTb);

    Integer updateOne(UserListenTb userListenTb);

    List<UserListenTb> findByUserId(Integer userId);
}
