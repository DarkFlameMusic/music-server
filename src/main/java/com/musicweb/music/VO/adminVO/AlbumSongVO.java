package com.musicweb.music.VO.adminVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicweb.music.VO.AlbumTbVO;
import com.musicweb.music.VO.SingerTbVO;

public class AlbumSongVO {

    @JsonProperty("album")
    private AlbumTbVO albumTbVO;

    @JsonProperty("creator")
    private SingerTbVO singerTbVO;

    public AlbumTbVO getAlbumTbVO() {
        return albumTbVO;
    }

    public void setAlbumTbVO(AlbumTbVO albumTbVO) {
        this.albumTbVO = albumTbVO;
    }

    public SingerTbVO getSingerTbVO() {
        return singerTbVO;
    }

    public void setSingerTbVO(SingerTbVO singerTbVO) {
        this.singerTbVO = singerTbVO;
    }
}
