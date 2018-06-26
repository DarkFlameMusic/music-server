package com.musicweb.music.VO;

import java.util.List;

public class MyMusicPageVO<T> {

    private Integer singerTotal;

    private Integer createSongListTotal;

    private Integer collectSongListTotal;

    private T mySongList;

    private List<SongListTbVO> createSongList;

    private List<SongListTbVO> collectSongList;

    public Integer getSingerTotal() {
        return singerTotal;
    }

    public void setSingerTotal(Integer singerTotal) {
        this.singerTotal = singerTotal;
    }

    public Integer getCreateSongListTotal() {
        return createSongListTotal;
    }

    public void setCreateSongListTotal(Integer createSongListTotal) {
        this.createSongListTotal = createSongListTotal;
    }

    public Integer getCollectSongListTotal() {
        return collectSongListTotal;
    }

    public void setCollectSongListTotal(Integer collectSongListTotal) {
        this.collectSongListTotal = collectSongListTotal;
    }

    public T getMySongList() {
        return mySongList;
    }

    public void setMySongList(T mySongList) {
        this.mySongList = mySongList;
    }

    public List<SongListTbVO> getCreateSongList() {
        return createSongList;
    }

    public void setCreateSongList(List<SongListTbVO> createSongList) {
        this.createSongList = createSongList;
    }

    public List<SongListTbVO> getCollectSongList() {
        return collectSongList;
    }

    public void setCollectSongList(List<SongListTbVO> collectSongList) {
        this.collectSongList = collectSongList;
    }
}
