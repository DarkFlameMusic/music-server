package com.musicweb.music.entity.commenttable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "album_comment_tb")
public class AlbumCommentTb {
    //评论id
    @Id
    @GeneratedValue
    private Integer albumCommentId;
    //专辑id
    private Integer albumId;
    //用户id
    private Integer userId;
    //评论
    private String comment;
    //创建时间
    private Date createTime;
    //点赞数
    private Integer admireNumber;

    public Integer getAlbumCommentId() {
        return albumCommentId;
    }

    public void setAlbumCommentId(Integer albumCommentId) {
        this.albumCommentId = albumCommentId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
