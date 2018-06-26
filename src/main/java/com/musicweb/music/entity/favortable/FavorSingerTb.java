package com.musicweb.music.entity.favortable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "favor_singer_tb")
public class FavorSingerTb {
    @Id
    @GeneratedValue
    //喜欢的歌手表id
    private Integer favorSingerId;
    //用户id
    private Integer userId;
    //喜欢的歌手id
    private Integer singerId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Integer getFavorSingerId() {
        return favorSingerId;
    }

    public void setFavorSingerId(Integer favorSingerId) {
        this.favorSingerId = favorSingerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
