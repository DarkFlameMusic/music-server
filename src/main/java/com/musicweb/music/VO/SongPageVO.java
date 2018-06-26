package com.musicweb.music.VO;

public class SongPageVO<T> {

    private Integer songId;

    private String songName;

    private Integer singerId;

    private String singerName;

    private Integer albumId;

    private String albumName;

    private Integer mvId;

    private String lyric;

    private String songImg;

    private String songUrl;

    private T songLiker;

    private T inSongList;

    private T similarSongs;

    private T songGoodComments;

    private T songComments;

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

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public T getSongLiker() {
        return songLiker;
    }

    public void setSongLiker(T songLiker) {
        this.songLiker = songLiker;
    }

    public T getInSongList() {
        return inSongList;
    }

    public void setInSongList(T inSongList) {
        this.inSongList = inSongList;
    }

    public T getSimilarSongs() {
        return similarSongs;
    }

    public void setSimilarSongs(T similarSongs) {
        this.similarSongs = similarSongs;
    }

    public T getSongGoodComments() {
        return songGoodComments;
    }

    public void setSongGoodComments(T songGoodComments) {
        this.songGoodComments = songGoodComments;
    }

    public T getSongComments() {
        return songComments;
    }

    public void setSongComments(T songComments) {
        this.songComments = songComments;
    }
}
