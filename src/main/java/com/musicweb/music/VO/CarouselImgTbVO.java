package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CarouselImgTbVO {

    @JsonProperty("id")
    private Integer carouselImgId;

    //轮播图
    @JsonProperty("imageUrl")
    private String carouselImg;
    //url
    @JsonProperty("redirectUrl")
    private String carouselUrl;

    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
