package com.musicweb.music.VO.SearchVO;

public class SearchResultVO<T> {

    private T songData;

    private T singerData;

    private T albumData;

    private T mvData;

    private T songListData;

    public T getSongData() {
        return songData;
    }

    public void setSongData(T songData) {
        this.songData = songData;
    }

    public T getSingerData() {
        return singerData;
    }

    public void setSingerData(T singerData) {
        this.singerData = singerData;
    }

    public T getAlbumData() {
        return albumData;
    }

    public void setAlbumData(T albumData) {
        this.albumData = albumData;
    }

    public T getMvData() {
        return mvData;
    }

    public void setMvData(T mvData) {
        this.mvData = mvData;
    }

    public T getSongListData() {
        return songListData;
    }

    public void setSongListData(T songListData) {
        this.songListData = songListData;
    }
}
