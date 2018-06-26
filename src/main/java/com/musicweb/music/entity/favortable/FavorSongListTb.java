package com.musicweb.music.entity.favortable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "favor_song_list_tb")
public class FavorSongListTb {
    @Id
    @GeneratedValue
    //喜欢的歌单表id
    private Integer favorSongListId;
    //用户id
    private Integer userId;
    //喜欢的歌单id
    private Integer songListId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Integer getFavorSongListId() {
        return favorSongListId;
    }

    public void setFavorSongListId(Integer favorSongListId) {
        this.favorSongListId = favorSongListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
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
