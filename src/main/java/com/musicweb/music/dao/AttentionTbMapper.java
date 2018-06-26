package com.musicweb.music.dao;

import com.musicweb.music.entity.AttentionTb;

import java.util.List;

public interface AttentionTbMapper {

    List<AttentionTb> findByUserId(Integer userId);

    AttentionTb findByAttentionId(Integer attentionId);

    int insertOne(AttentionTb attentionTb);

    int deleteByAttentionId(Integer attentionId);

    int updateByAttentionId(Integer attentionId);
}
