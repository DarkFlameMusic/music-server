package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "attention")
public class AttentionTb {

    @Id
    @GeneratedValue
    //关注表id
    private Integer attentionId;
    //用户id
    private Integer userId;
    //被关注的用户id
    private Integer userAttentionId;
    //创建时间
    private Date createTime;

    public Integer getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAttentionId() {
        return userAttentionId;
    }

    public void setUserAttentionId(Integer userAttentionId) {
        this.userAttentionId = userAttentionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AttentionTb{" +
                "attentionId=" + attentionId +
                ", userId=" + userId +
                ", userAttentionId=" + userAttentionId +
                ", createTime=" + createTime +
                '}';
    }
}
