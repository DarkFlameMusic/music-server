package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "song_list_song_tb")
public class SongListSongTb {


    @Id
    @GeneratedValue
    //歌单歌曲表id
    private Integer songListSongId;
    //歌单id
    private Integer songListId;
    //歌曲id
    private Integer songId;
    //创建时间
    private Date createTime;

    public Integer getSongListSongId() {
        return songListSongId;
    }

    public void setSongListSongId(Integer songListSongId) {
        this.songListSongId = songListSongId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
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
}
