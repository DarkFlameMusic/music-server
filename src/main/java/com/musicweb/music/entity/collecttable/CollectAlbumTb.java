package com.musicweb.music.entity.collecttable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "collect_album_tb")
public class CollectAlbumTb {


    @Id
    @GeneratedValue
    //收藏的专辑id
    private Integer collectAlbumId;
    //用户id
    private Integer userId;
    //专辑id
    private Integer albumId;
    //创建时间
    private Date createTime;

    public Integer getCollectAlbumId() {
        return collectAlbumId;
    }

    public void setCollectAlbumId(Integer collectAlbumId) {
        this.collectAlbumId = collectAlbumId;
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
}
