package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "song_tb")
public class SongTb {

    @Id
    @GeneratedValue
    //歌曲id
    private Integer songId;
    //歌手id
    private Integer singerId;
    //歌名
    private String songName;
    //专辑id
    private Integer albumId;

    private Integer mvId;
    //时长
    private Integer songTime;
    //歌曲风格
    private String singStyle;
    //语言
    private String language;
    //评论数
    private Integer commentNumber;
    //播放数
    private Integer playNumber;
    //创建时间
    private Date createTime;
    //歌词
    private String lyric;
    //歌曲链接
    private String songUrl;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public Integer getSongTime() {
        return songTime;
    }

    public void setSongTime(Integer songTime) {
        this.songTime = songTime;
    }

    public String getSingStyle() {
        return singStyle;
    }

    public void setSingStyle(String singStyle) {
        this.singStyle = singStyle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    @Override
    public String toString() {
        return "SongTb{" +
                "songId=" + songId +
                ", singerId=" + singerId +
                ", songName='" + songName + '\'' +
                ", albumId=" + albumId +
                ", mvId=" + mvId +
                ", songTime=" + songTime +
                ", singStyle='" + singStyle + '\'' +
                ", language='" + language + '\'' +
                ", commentNumber=" + commentNumber +
                ", playNumber=" + playNumber +
                ", createTime=" + createTime +
                ", lyric='" + lyric + '\'' +
                ", songUrl='" + songUrl + '\'' +
                '}';
    }
}
