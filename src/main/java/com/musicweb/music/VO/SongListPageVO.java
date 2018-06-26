package com.musicweb.music.VO;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SongListPageVO<T> {

    @JsonProperty("songlistName")
    private String songListName;
    @JsonProperty("songlistId")
    private Integer songListId;
    @JsonProperty("songlistDesc")
    private String songListIntro;
    @JsonProperty("songlistTags")
    private String label;
    @JsonProperty("songlistStore")
    private Integer collectNumber;
    @JsonProperty("songlistEnjoy")
    private Integer shareNumber;
    @JsonProperty("songlistComment")
    private Integer commentNumber;
    @JsonProperty("songlistPlayCount")
    private Integer playNumber;
    @JsonProperty("songlistCrreateTime")
    private String createTime;
    @JsonProperty("songlsitImage")
    private String songListImg;
    @JsonProperty("songlistCreaterId")
    private Integer userId;
    @JsonProperty("songlistCreaterName")
    private String userNickname;
    @JsonProperty("songlistCreaterAvatar")
    private String headImg;

    private Integer songTotal;
    @JsonProperty("songlistSongs")
    private T songListSongs;
    @JsonProperty("songlistGoodComments")
    private T songListGoodComment;
    @JsonProperty("songlistComments")
    private T songListComments;
    @JsonProperty("songlistLikers")
    private T songListCollect;
    @JsonProperty("relRecommond")
    private T similaritySongList;

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

    public String getSongListIntro() {
        return songListIntro;
    }

    public void setSongListIntro(String songListIntro) {
        this.songListIntro = songListIntro;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getSongTotal() {
        return songTotal;
    }

    public void setSongTotal(Integer songTotal) {
        this.songTotal = songTotal;
    }

    public T getSongListSongs() {
        return songListSongs;
    }

    public void setSongListSongs(T songListSongs) {
        this.songListSongs = songListSongs;
    }

    public T getSongListGoodComment() {
        return songListGoodComment;
    }

    public void setSongListGoodComment(T songListGoodComment) {
        this.songListGoodComment = songListGoodComment;
    }

    public T getSongListComments() {
        return songListComments;
    }

    public void setSongListComments(T songListComments) {
        this.songListComments = songListComments;
    }

    public T getSongListCollect() {
        return songListCollect;
    }

    public void setSongListCollect(T songListCollect) {
        this.songListCollect = songListCollect;
    }

    public T getSimilaritySongList() {
        return similaritySongList;
    }

    public void setSimilaritySongList(T similaritySongList) {
        this.similaritySongList = similaritySongList;
    }
}
