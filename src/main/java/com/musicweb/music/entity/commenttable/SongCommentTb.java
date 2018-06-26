package com.musicweb.music.entity.commenttable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "song_comment_tb")
public class SongCommentTb {


    @Id
    @GeneratedValue
    //歌曲评论id
    private Integer songCommentId;
    //歌曲id
    private Integer songId;
    //用户id
    private Integer userId;
    //评论
    private String comment;
    //创建时间
    private Date createTime;
    //点赞数
    private Integer admireNumber;

    public Integer getSongCommentId() {
        return songCommentId;
    }

    public void setSongCommentId(Integer songCommentId) {
        this.songCommentId = songCommentId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
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
