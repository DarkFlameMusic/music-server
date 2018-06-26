package com.musicweb.music.entity.commenttable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "song_list_comment_tb")
public class SongListCommentTb {

    @Id
    @GeneratedValue
    //歌单评论id
    private Integer songListCommentId;
    //歌单id
    private Integer songListId;
    //用户id
    private Integer userId;
    //评论
    private String comment;
    //创建时间
    private Date createTime;
    //点赞数
    private Integer admireNumber;

    public Integer getSongListCommentId() {
        return songListCommentId;
    }

    public void setSongListCommentId(Integer songListCommentId) {
        this.songListCommentId = songListCommentId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
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
