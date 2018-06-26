package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongListTbVO<T> {

    //歌单id
    @JsonProperty("songlistId")
    private Integer songListId;
    //歌单名
    @JsonProperty("songlistName")
    private String songListName;
    //用户id
    private Integer userId;
    //呢称
    @JsonProperty("nickName")
    private String userNickname;
    //标签
    @JsonProperty("tag")
    private String label;
    //歌单图
    @JsonProperty("image")
    private String songListImg;
    //播放数
    @JsonProperty("playCount")
    private String playNumber;

    private String songTotal;


    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
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

    public String getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(String playNumber) {
        this.playNumber = playNumber;
    }

    public String getSongTotal() {
        return songTotal;
    }

    public void setSongTotal(String songTotal) {
        this.songTotal = songTotal;
    }

}
