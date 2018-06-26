package com.musicweb.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_listen_tb")
public class UserListenTb {

    @Id
    @GeneratedValue
    private Integer userListenId;

    private Integer userId;

    private Integer songId;

    private Integer playNumber;

    private Date createTime;

    private Date updateTime;

    public Integer getUserListenId() {
        return userListenId;
    }

    public void setUserListenId(Integer userListenId) {
        this.userListenId = userListenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
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

    @Override
    public String toString() {
        return "UserListenTb{" +
                "userListenId=" + userListenId +
                ", userId=" + userId +
                ", songId=" + songId +
                ", playNumber=" + playNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
