package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SongListCommentDataVO {

    private Integer userId;

    private String userName;
    @JsonProperty("userAvatar")
    private String headImg;
    @JsonProperty("content")
    private String comment;

    private String createTime;
    @JsonProperty("likeCount")
    private Integer admireNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getAdmireNumber() {
        return admireNumber;
    }

    public void setAdmireNumber(Integer admireNumber) {
        this.admireNumber = admireNumber;
    }
}
