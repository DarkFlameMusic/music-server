package com.musicweb.music.VO.SearchVO;


public class SearchUserResultVO {

    private Integer userId;

    private String userNickname;

    private String headImg;

    private String personIntro;

    private String fanNumber;

    private Integer songListNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPersonIntro() {
        return personIntro;
    }

    public void setPersonIntro(String personIntro) {
        this.personIntro = personIntro;
    }

    public String getFanNumber() {
        return fanNumber;
    }

    public void setFanNumber(String fanNumber) {
        this.fanNumber = fanNumber;
    }

    public Integer getSongListNumber() {
        return songListNumber;
    }

    public void setSongListNumber(Integer songListNumber) {
        this.songListNumber = songListNumber;
    }
}
