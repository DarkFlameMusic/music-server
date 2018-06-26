package com.musicweb.music.controller;



import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.SongPageVO;
import com.musicweb.music.VO.SongTbVO;
import com.musicweb.music.entity.SongTb;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.AlbumTbServiceImpl;
import com.musicweb.music.service.impl.CommentTbServiceImpl;
import com.musicweb.music.service.impl.SingerTbServiceImpl;
import com.musicweb.music.service.impl.SongTbServiceImpl;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SongController extends BasePageController{

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private CommentTbServiceImpl commentTbService;

    //歌曲页
    @ApiOperation(value = "歌曲页")
    @GetMapping(value = "/songpage")
    public ResultVO songPage(@RequestParam("songId") Integer songId){
        SongTb songTb = songTbService.findBySongId(songId);
        SongPageVO songPageVO = new SongPageVO();
        BeanUtils.copyProperties(songTb,songPageVO);
        songPageVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
        songPageVO.setAlbumName(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumName());
        songPageVO.setSongImg(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumImg());
        songPageVO.setSongLiker(getLiker(songId, CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSimilarSongs(getSimilar(songTb.getSongName(),CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setInSongList(getInclude(songId,CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSongComments(getCommentPage(1,songId,CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSongGoodComments(handleComment(commentTbService.findSortAdmireNumber(songId,CommentTypeEnum.SONG_COMMENT.getCode(),100)));

        return ResultVOUtil.success(songPageVO);
    }
}
