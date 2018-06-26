package com.musicweb.music.VO.adminVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicweb.music.VO.SingerTbVO;
import com.musicweb.music.VO.SongTbVO;

import java.util.Date;

public class SingerSongVO {

    @JsonProperty("singer")
    private SingerTbVO singerTbVO;

    @JsonProperty("song")
    private SongTbVO songTbVO;

    public SingerTbVO getSingerTbVO() {
        return singerTbVO;
    }

    public void setSingerTbVO(SingerTbVO singerTbVO) {
        this.singerTbVO = singerTbVO;
    }

    public SongTbVO getSongTbVO() {
        return songTbVO;
    }

    public void setSongTbVO(SongTbVO songTbVO) {
        this.songTbVO = songTbVO;
    }
}
