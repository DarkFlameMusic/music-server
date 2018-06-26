package com.musicweb.music.entity.collecttable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "collect_song_list_tb")
public class CollectSongListTb {

    @Id
    @GeneratedValue
    //收藏的歌单id
    private Integer collectSongListId;
    //用户id
    private Integer userId;
    //歌单id
    private Integer songListId;
    //创建时间
    private Date createTime;

    public Integer getCollectSongListId() {
        return collectSongListId;
    }

    public void setCollectSongListId(Integer collectSongListId) {
        this.collectSongListId = collectSongListId;
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

    @Override
    public String toString() {
        return "CollectSongListTb{" +
                "collectSongListId=" + collectSongListId +
                ", userId=" + userId +
                ", songListId=" + songListId +
                ", createTime=" + createTime +
                '}';
    }
}
