package com.musicweb.music.VO.adminVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicweb.music.VO.UserTbVO;
import com.musicweb.music.entity.UserTb;

public class UserSongListVO {

    @JsonProperty("songlist")
    private SongListSongVO songListSongVO;

    @JsonProperty("creator")
    private UserTbVO userTbVO;

    public SongListSongVO getSongListSongVO() {
        return songListSongVO;
    }

    public void setSongListSongVO(SongListSongVO songListSongVO) {
        this.songListSongVO = songListSongVO;
    }

    public UserTbVO getUserTbVO() {
        return userTbVO;
    }

    public void setUserTbVO(UserTbVO userTbVO) {
        this.userTbVO = userTbVO;
    }
}
