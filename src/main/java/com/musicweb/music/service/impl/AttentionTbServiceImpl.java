package com.musicweb.music.service.impl;

import com.musicweb.music.dao.AttentionTbMapper;
import com.musicweb.music.dao.UserTbMapper;
import com.musicweb.music.entity.AttentionTb;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.service.AttentionTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AttentionTbServiceImpl implements AttentionTbService{

    @Autowired
    private AttentionTbMapper mapper;

    @Autowired
    private UserTbMapper userTbMapper;

    @Override
    public List<AttentionTb> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public Integer insertOne(AttentionTb attentionTb) {
        Integer result = 0;
        result = mapper.insertOne(attentionTb);
        UserTb userTb = userTbMapper.findByUserId(attentionTb.getUserId());
        UserTb userAttentionTb = userTbMapper.findByUserId(attentionTb.getUserAttentionId());
        if (userAttentionTb.getFanNumber() == null){
            userAttentionTb.setFanNumber(0);
        }
        if (userTb.getAttentionNumber() == null || userTb.getAttentionNumber() < 0){
            userTb.setAttentionNumber(0);
        }
        userAttentionTb.setFanNumber(userAttentionTb.getFanNumber()+1);
        userTb.setAttentionNumber(userTb.getAttentionNumber()+1);
        userTbMapper.updateByUserId(userTb);
        userTbMapper.updateByUserId(userAttentionTb);
        return result;
    }

    @Override
    @Transactional
    public Integer deleteByAttentionId(Integer attentionId) {
        Integer result = 0;
        UserTb userTb = userTbMapper.findByUserId(mapper.findByAttentionId(attentionId).getUserId());
        UserTb userAttentionTb = userTbMapper.findByUserId(mapper.findByAttentionId(attentionId).getUserAttentionId());
        Integer fanNumber = userAttentionTb.getFanNumber();
        fanNumber--;
        userAttentionTb.setFanNumber(fanNumber);
        Integer attentionNumber = userTb.getAttentionNumber();
        attentionNumber--;
        userTb.setAttentionNumber(attentionNumber);
        userTbMapper.updateByUserId(userTb);
        userTbMapper.updateByUserId(userAttentionTb);
        result = mapper.deleteByAttentionId(attentionId);
        return result;
    }
}
