package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlbumTbVO {


    //专辑名
    @JsonProperty("name")
    private String albumName;
    //专辑id
    @JsonProperty("id")
    private Integer albumId;
    //歌手名
//    @JsonProperty("singer")
//    private String singerName;
//    //歌手id
//    private Integer singerId;
    //专辑图
    @JsonProperty("image")
    private String albumImg;


    @JsonProperty("playCount")
    private Integer playNumber;

    @JsonProperty("updateTime")
    private String issueTime;

    private List<SongTbVO> songs;

    public List<SongTbVO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongTbVO> songs) {
        this.songs = songs;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

//    public String getSingerName() {
//        return singerName;
//    }
//
//    public void setSingerName(String singerName) {
//        this.singerName = singerName;
//    }
//
//    public Integer getSingerId() {
//        return singerId;
//    }
//
//    public void setSingerId(Integer singerId) {
//        this.singerId = singerId;
//    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }
}
