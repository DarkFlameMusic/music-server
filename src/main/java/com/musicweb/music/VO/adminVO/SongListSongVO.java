package com.musicweb.music.VO.adminVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicweb.music.VO.SongTbVO;

import java.util.Date;
import java.util.List;

public class SongListSongVO {

    //歌单id
    @JsonProperty("id")
    private Integer songListId;
    //歌单名
    @JsonProperty("name")
    private String songListName;


    //标签
    @JsonProperty("labels")
    private String[] label;
    //歌单图
    @JsonProperty("image")
    private String songListImg;
    //创建时间
    private String createTime;

    private String updateTime;

    //播放数
    @JsonProperty("playCount")
    private Integer playNumber;

    private Integer songCount;
    @JsonProperty("songs")
    private List<SongTbVO> songTbVOList;

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public List<SongTbVO> getSongTbVOList() {
        return songTbVOList;
    }

    public void setSongTbVOList(List<SongTbVO> songTbVOList) {
        this.songTbVOList = songTbVOList;
    }
}
