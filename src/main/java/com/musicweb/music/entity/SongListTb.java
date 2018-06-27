package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "song_list_tb")
public class SongListTb {
    @Id
    @GeneratedValue
    //歌单id
    private Integer songListId;
    //歌单名
    private String songListName;
    //歌单介绍
    private String songListIntro;
    //用户id
    private Integer userId;
    //标签
    private String label;
    //歌单图
    private String songListImg;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //评论数
    private Integer commentNumber;
    //播放数
    private Integer playNumber;
    //分享数
    private Integer shareNumber;
    //收藏数
    private Integer collectNumber;

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public String getSongListIntro() {
        return songListIntro;
    }

    public void setSongListIntro(String songListIntro) {
        this.songListIntro = songListIntro;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}
