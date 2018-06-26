package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingerTbVO {


    //歌手名
    @JsonProperty("name")
    private String singerName;
    //歌手id
    @JsonProperty("id")
    private Integer singerId;
    //用户id
    private Integer userId;
    //歌手一句介绍
    @JsonProperty("desc")
    private String singerOneIntro;
    //歌手图
    @JsonProperty("avatar")
    private String singerImg;

    @JsonProperty("albumCount")
    private String albumNumber;

//    private String mvNumber;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSingerOneIntro() {
        return singerOneIntro;
    }

    public void setSingerOneIntro(String singerOneIntro) {
        this.singerOneIntro = singerOneIntro;
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

//    public String getMvNumber() {
//        return mvNumber;
//    }
//
//    public void setMvNumber(String mvNumber) {
//        this.mvNumber = mvNumber;
//    }
}
