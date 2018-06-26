package com.musicweb.music.VO;

import java.util.Date;

public class CommentResultVO {

    private Integer commentId;

    private Integer userId;

    private String userNickname;

    private String headImg;

    private Integer objectType;

    private String comment;

    private String createTime;

    private Integer admireNumber;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
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

    @Override
    public String toString() {
        return "CommentResultVO{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", userNickname='" + userNickname + '\'' +
                ", headImg='" + headImg + '\'' +
                ", objectType=" + objectType +
                ", comment='" + comment + '\'' +
                ", createTime='" + createTime + '\'' +
                ", admireNumber=" + admireNumber +
                '}';
    }
}
