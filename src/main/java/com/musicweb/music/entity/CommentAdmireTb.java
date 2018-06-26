package com.musicweb.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comment_admire_tb")
public class CommentAdmireTb {

    @Id
    @GeneratedValue
    private Integer commentAdmireId;

    private Integer userId;

    private Integer commentId;

    private Integer commentType;

    private Date createTime;

    public Integer getCommentAdmireId() {
        return commentAdmireId;
    }

    public void setCommentAdmireId(Integer commentAdmireId) {
        this.commentAdmireId = commentAdmireId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
