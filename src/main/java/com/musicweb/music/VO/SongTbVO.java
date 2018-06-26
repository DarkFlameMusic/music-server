package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongTbVO {

    @JsonProperty("id")
    private Integer songId;

    @JsonProperty("name")
    private String songName;

//    private Integer singerId;
//
//    private String singerName;
//
//    private Integer albumId;
//
//    private String albumName;

    @JsonProperty("time")
    private Integer songTime;

//    private Integer mvId;

    @JsonProperty("lyrics")
    private String lyric;

    @JsonProperty("url")
    private String songUrl;

    private String[] labels;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

//    public Integer getSingerId() {
//        return singerId;
//    }
//
//    public void setSingerId(Integer singerId) {
//        this.singerId = singerId;
//    }
//
//    public String getSingerName() {
//        return singerName;
//    }
//
//    public void setSingerName(String singerName) {
//        this.singerName = singerName;
//    }
//
//    public Integer getAlbumId() {
//        return albumId;
//    }
//
//    public void setAlbumId(Integer albumId) {
//        this.albumId = albumId;
//    }
//
//    public String getAlbumName() {
//        return albumName;
//    }
//
//    public void setAlbumName(String albumName) {
//        this.albumName = albumName;
//    }

    public Integer getSongTime() {
        return songTime;
    }

    public void setSongTime(Integer songTime) {
        this.songTime = songTime;
    }

//    public Integer getMvId() {
//        return mvId;
//    }
//
//    public void setMvId(Integer mvId) {
//        this.mvId = mvId;
//    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
