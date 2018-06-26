package com.musicweb.music.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "carousel_img_tb")
public class CarouselImgTb {

    @Id
    @GeneratedValue
    //轮播图id
    private Integer carouselImgId;
    //轮播图
    private String carouselImg;
    //url
    private String carouselUrl;
    //创建时间
    private Date createTime;

    public Integer getCarouselImgId() {
        return carouselImgId;
    }

    public void setCarouselImgId(Integer carouselImgId) {
        this.carouselImgId = carouselImgId;
    }

    public String getCarouselImg() {
        return carouselImg;
    }

    public void setCarouselImg(String carouselImg) {
        this.carouselImg = carouselImg;
    }

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
