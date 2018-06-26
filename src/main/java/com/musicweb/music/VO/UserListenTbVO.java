package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserListenTbVO {

    private Integer userId;

    private String songName;

    private Integer singerId;

    private String singerNmae;
    @JsonProperty("playCount")
    private Integer playNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerNmae() {
        return singerNmae;
    }

    public void setSingerNmae(String singerNmae) {
        this.singerNmae = singerNmae;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }
}
