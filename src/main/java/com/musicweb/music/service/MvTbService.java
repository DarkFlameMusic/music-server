package com.musicweb.music.service;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.entity.MvTb;

import java.util.List;

public interface MvTbService {

    PageInfo<MvTb> searchByMvName(String content, Integer pageNumber, Integer pageSize);

    PageInfo<MvTb> findBySingerId(Integer singerId, Integer pageNumber, Integer pageSize);
}
