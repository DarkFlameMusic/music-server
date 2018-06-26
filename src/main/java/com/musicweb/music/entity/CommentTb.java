package com.musicweb.music.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class CommentTb {

    @Id
    @GeneratedValue
    private Integer commentId;

    private Integer objectId;

    private Integer userId;

    private Integer objectType;

    private String comment;

    private Date createTime;

    private Integer admireNumber;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        return "CommentTb{" +
                "commentId=" + commentId +
                ", objectId=" + objectId +
                ", userId=" + userId +
                ", objectType=" + objectType +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", admireNumber=" + admireNumber +
                '}';
    }
}
