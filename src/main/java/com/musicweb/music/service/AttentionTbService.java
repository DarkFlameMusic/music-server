package com.musicweb.music.service;

import com.musicweb.music.entity.AttentionTb;

import java.util.List;

public interface AttentionTbService {

    List<AttentionTb> findByUserId(Integer userId);

    Integer insertOne(AttentionTb attentionTb);

    Integer deleteByAttentionId(Integer attentionId);
}
