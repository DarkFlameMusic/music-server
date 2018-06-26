package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "mv_tb")
public class MvTb {

    @Id
    @GeneratedValue
    //mv id
    private Integer mvId;

    private Integer singerId;

    private String mvName;
    //评论数
    private Integer commentNumber;
    //播放次数
    private Integer playNumber;
    //分享次数
    private Integer shareNumber;
    //收藏次数
    private Integer collectNumber;
    //创建时间
    private Date createTime;

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getMvName() {
        return mvName;
    }

    public void setMvName(String mvName) {
        this.mvName = mvName;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MvTb{" +
                "mvId=" + mvId +
                ", mvName='" + mvName + '\'' +
                ", commentNumber=" + commentNumber +
                ", playNumber=" + playNumber +
                ", shareNumber=" + shareNumber +
                ", collectNumber=" + collectNumber +
                ", createTime=" + createTime +
                '}';
    }
}
