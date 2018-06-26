package com.musicweb.music.VO;

public class AlbumTbPageVO<T> {

    private Integer albumId;

    private String albumName;

    private Integer singerId;

    private String singerName;

    private String singerImg;

    private String companyName;

    private String issueTime;

    private String albumImg;

    private Integer songTotal;

    private T albumLiker;

    private T inAlbum;

    private T similarAlbum;

    private T albumGoodComment;

    private T albumComment;


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

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public Integer getSongTotal() {
        return songTotal;
    }

    public void setSongTotal(Integer songTotal) {
        this.songTotal = songTotal;
    }

    public T getAlbumLiker() {
        return albumLiker;
    }

    public void setAlbumLiker(T albumLiker) {
        this.albumLiker = albumLiker;
    }

    public T getInAlbum() {
        return inAlbum;
    }

    public void setInAlbum(T inAlbum) {
        this.inAlbum = inAlbum;
    }

    public T getSimilarAlbum() {
        return similarAlbum;
    }

    public void setSimilarAlbum(T similarAlbum) {
        this.similarAlbum = similarAlbum;
    }

    public T getAlbumGoodComment() {
        return albumGoodComment;
    }

    public void setAlbumGoodComment(T albumGoodComment) {
        this.albumGoodComment = albumGoodComment;
    }

    public T getAlbumComment() {
        return albumComment;
    }

    public void setAlbumComment(T albumComment) {
        this.albumComment = albumComment;
    }
}
