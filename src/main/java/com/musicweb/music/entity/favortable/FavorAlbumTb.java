package com.musicweb.music.entity.favortable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "favor_album_tb")
public class FavorAlbumTb {

    @Id
    @GeneratedValue
    //喜欢表id
    private Integer favorAlbumId;
    //用户id
    private Integer userId;
    //喜欢的专辑id
    private Integer albumId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Integer getFavorAlbumId() {
        return favorAlbumId;
    }

    public void setFavorAlbumId(Integer favorAlbumId) {
        this.favorAlbumId = favorAlbumId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
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
