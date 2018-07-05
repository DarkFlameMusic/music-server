package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongListTbCreateVO {


    @JsonProperty("songlistNmae")
    private String songListName;
    @JsonProperty("songlsitId")
    private Integer songListId;
    @JsonProperty("tag")
    private String label;
    @JsonProperty("image")
    private String songListImg;
    @JsonProperty("playCount")
    private Integer playNumber;

    private String userNickname;

    private Integer userId;

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
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

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
