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
import com.musicweb.music.utils.CookieUtil;
import com.musicweb.music.utils.ResultVOUtil;
import com.musicweb.music.utils.TokenUtil;
import com.musicweb.music.utils.UploadUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SongController extends BasePageController {

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
    public ResultVO songPage(@RequestParam("songId") Integer songId) {
        SongTb songTb = songTbService.findBySongId(songId);
        SongPageVO songPageVO = new SongPageVO();
        BeanUtils.copyProperties(songTb, songPageVO);
        songPageVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
        songPageVO.setAlbumName(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumName());
        songPageVO.setSongImg(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumImg());
        songPageVO.setSongLiker(getLiker(songId, CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSimilarSongs(getSimilar(songTb.getSongName(), CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setInSongList(getInclude(songId, CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSongComments(getCommentPage(1, songId, CommentTypeEnum.SONG_COMMENT.getCode()));
        songPageVO.setSongGoodComments(handleComment(commentTbService.findSortAdmireNumber(songId, CommentTypeEnum.SONG_COMMENT.getCode(), 100)));

        return ResultVOUtil.success(songPageVO);
    }

    @ApiOperation(value = "歌曲页")
    @PostMapping(value = "/addSong")
    public ResultVO addSong(@RequestParam("songName") String songName,
                            @RequestParam("songTime") Integer songTime,
                            @RequestParam("language") String language,
                            @RequestParam("albumId") Integer albumId,
                            @RequestParam("labels") String labels,
                            @RequestParam("file") MultipartFile songFile,
                            @RequestParam("songLyric") String songLyric,
                            @RequestParam("singerId") Integer singerId) throws IOException {

        SongTb songTb = new SongTb();
        FileInputStream fileInputStream = (FileInputStream) songFile.getInputStream();
        String songUrl = UploadUtil.commonUpload(fileInputStream, songFile.getOriginalFilename());
        songTb.setSongUrl(songUrl);
        songTb.setAlbumId(albumId);
        songTb.setLanguage(language);
        songTb.setLyric(songLyric);
        songTb.setSingerId(singerId);
        songTb.setSongName(songName);
        songTb.setSongTime(songTime);
        songTb.setSingStyle(labels);

        int code = songTbService.insertOne(songTb);

        return ResultVOUtil.success();
    }

    @ApiOperation(value = "删除歌曲")
    @GetMapping(value = "/deleteSong")
    public ResultVO deleteSong(@RequestParam("id") Integer songId) {

        if (songTbService.deleteOne(songId) == 1) {
            return ResultVOUtil.success();
        } else {
            return ResultVOUtil.error(ExceptionEnum.DELETE_ERROR.getCode(),"删除歌曲失败");
        }
    }

    @ApiOperation(value = "更新歌曲")
    @PostMapping(value = "/updateSong")
    public ResultVO updateSong(@RequestParam("id") Integer songId,
                               @RequestParam(value = "songFile",required = false) MultipartFile songFile,
                               @RequestParam(value = "songName", required = false) String songName,
                               @RequestParam(value = "singerId", required = false) Integer singerId,
                               @RequestParam(value = "labels", required = false) String labels,
                               @RequestParam(value = "lyrics", required = false) String lyrics) throws IOException {

        SongTb songTb = songTbService.findBySongId(songId);
        if(songFile!=null){
            FileInputStream fileInputStream = (FileInputStream) songFile.getInputStream();
            String songUrl = UploadUtil.commonUpload(fileInputStream, songFile.getOriginalFilename());
            songTb.setSongUrl(songUrl);
        }
        if(songName!=null){
            songTb.setSongName(songName);
        }
        if(singerId!=null){
            songTb.setSingerId(singerId);
        }
        if(labels!=null){
            songTb.setSingStyle(labels);
        }
        if(lyrics!=null){
            songTb.setLyric(lyrics);
        }
        if(songTbService.updateOne(songTb)==1){
            return ResultVOUtil.success(songTb);
        }else{
            return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR.getCode(),ExceptionEnum.UPDATE_ERROR.getMsg());
        }
    }
}
