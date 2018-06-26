package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongListTbRelatedVO {

    @JsonProperty("songlistId")
    private Integer songListId;
    @JsonProperty("songlistImage")
    private String songListImg;
    @JsonProperty("songlistName")
    private String songListName;
    @JsonProperty("songlistCreaterId")
    private Integer userId;
    @JsonProperty("songlistCreaterName")
    private String userNickname;

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
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
}
