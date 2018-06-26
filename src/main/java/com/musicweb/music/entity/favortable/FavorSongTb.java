package com.musicweb.music.entity.favortable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "favor_song_tb")
public class FavorSongTb {
    @Id
    @GeneratedValue
    //喜欢的歌曲表id
    private Integer favorSongId;
    //用户id
    private Integer userId;
    //喜欢的歌曲id
    private Integer songId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Integer getFavorSongId() {
        return favorSongId;
    }

    public void setFavorSongId(Integer favorSongId) {
        this.favorSongId = favorSongId;
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
}
