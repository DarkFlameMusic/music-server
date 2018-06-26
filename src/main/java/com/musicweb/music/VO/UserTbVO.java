package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserTbVO {

    //用户id
    @JsonProperty("id")
    private Integer userId;
    //用户名
    @JsonProperty("username")
    private String username;
    //昵称
    @JsonProperty("name")
    private String userNickname;
    //邮箱
    @JsonProperty("email")
    private String mail;
    //手机号
//    @JsonProperty("telephone")
    private String phoneNumber;
    //头像
    @JsonProperty("avatar")
    private String headImg;
    //出生日期
//    @JsonProperty("birthday")
    private String birthDate;
    //所在地区
    private String area;
    //个人介绍
//    @JsonProperty("description")
    private String personIntro;
    //关注数
    @JsonProperty("followers")
    private Integer attentionNumber;
    //被关注数
    @JsonProperty("stars")
    private Integer fanNumber;

    private String createTime;

    private String updateTime;

    private String[] roles;

    @JsonProperty("status")
    private Integer jurisdiction;

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonIntro() {
        return personIntro;
    }

    public void setPersonIntro(String personIntro) {
        this.personIntro = personIntro;
    }

    public Integer getAttentionNumber() {
        return attentionNumber;
    }

    public void setAttentionNumber(Integer attentionNumber) {
        this.attentionNumber = attentionNumber;
    }

    public Integer getFanNumber() {
        return fanNumber;
    }

    public void setFanNumber(Integer fanNumber) {
        this.fanNumber = fanNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
