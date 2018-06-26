package com.musicweb.music.entity.favortable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "favor_mv_tb")
public class FavorMvTb {
    @Id
    @GeneratedValue
    //喜欢mv表id
    private Integer favorMvId;
    //用户id
    private Integer userId;
    //喜欢的mv id
    private Integer mvId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Integer getFavorMvId() {
        return favorMvId;
    }

    public void setFavorMvId(Integer favorMvId) {
        this.favorMvId = favorMvId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
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
