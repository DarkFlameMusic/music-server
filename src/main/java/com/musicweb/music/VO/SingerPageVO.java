package com.musicweb.music.VO;

public class SingerPageVO<T> {
    //歌手id
    private Integer singerId;
    //用户id
    private Integer userId;
    //歌手名
    private String singerName;
    //歌手图
    private String singerImg;
    //歌手一句介绍
    private String singerOneIntro;
    //歌手介绍
    private String singerIntro;
    //类型
    private Integer singerType;
    //性别
    private Integer gender;
    //名字首字母
    private String initial;
    //人气
    private Integer popularity;

    private T similarSinger;

    private T data;

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }

    public String getSingerOneIntro() {
        return singerOneIntro;
    }

    public void setSingerOneIntro(String singerOneIntro) {
        this.singerOneIntro = singerOneIntro;
    }

    public String getSingerIntro() {
        return singerIntro;
    }

    public void setSingerIntro(String singerIntro) {
        this.singerIntro = singerIntro;
    }

    public Integer getSingerType() {
        return singerType;
    }

    public void setSingerType(Integer singerType) {
        this.singerType = singerType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public T getSimilarSinger() {
        return similarSinger;
    }

    public void setSimilarSinger(T similarSinger) {
        this.similarSinger = similarSinger;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
