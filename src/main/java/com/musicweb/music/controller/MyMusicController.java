package com.musicweb.music.controller;


import com.musicweb.music.VO.*;
import com.musicweb.music.entity.SingerTb;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.entity.UserListenTb;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.entity.collecttable.CollectSongListTb;
import com.musicweb.music.entity.favortable.FavorSingerTb;
import com.musicweb.music.entity.favortable.FavorSongTb;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.CookieUtil;
import com.musicweb.music.utils.DateUtil;
import com.musicweb.music.utils.ResultVOUtil;
import com.musicweb.music.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mymusic")
public class MyMusicController extends BasePageController {

    @Autowired
    private FavorSingerTbServiceImpl favorSingerTbService;

    @Autowired
    private FavorSongTbServiceImpl favorSongTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private MvTbServiceImpl mvTbService;

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private UserListenTbServiceImpl userListenTbService;


    //TODO 我的歌手详情页
    @ApiOperation(value = "关注的歌手",notes = "关注的歌手详情")
    @GetMapping(value = "/artist")
    public ResultVO artist(HttpServletRequest request) {
        Integer userId = null;
        try {
            Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
            userId = Integer.valueOf(claims.getId());
        }catch (Exception e){
            throw new MusicException(ExceptionEnum.LOGIN_NULL);
        }


        List<FavorSingerTb> favorSingerTbList = favorSingerTbService.findByUserId(userId);
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        for (FavorSingerTb favorSingerTb : favorSingerTbList) {
            SingerTb singerTb = singerTbService.findBySingerId(favorSingerTb.getSingerId());
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTb, singerTbVO);
            singerTbVO.setAlbumNumber(albumTbService.findBySingerId(favorSingerTb.getSingerId()).size() + "个专辑");
//            singerTbVO.setMvNumber(mvTbService.findBySingerId(favorSingerTb.getSingerId(),1,50).getList().size() + "个MV");
            singerTbVOList.add(singerTbVO);
        }

        return ResultVOUtil.success(singerTbVOList);
    }


    //TODO 未测试  用户创建的歌单
    @ApiOperation(value = "我的音乐页面")
    @GetMapping(value = "/mysonglist")
    public ResultVO mySongList(HttpServletRequest request) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        MyMusicPageVO myMusicPageVO = new MyMusicPageVO();
        UserTb userTb = userTbService.findById(userId);
        List<FavorSongTb> favorSongTbList = favorSongTbService.findByUserId(userId);
        List<SongTbVO> songTbVOList = new ArrayList<>();
        SongListPageVO songListPageVO = new SongListPageVO();
        songListPageVO.setSongListName("我喜欢的音乐");

//        songListPageVO.setUserId(userId);
//        songListPageVO.setUserNickname(userTb.getUserNickname());
        List<UserListenTb> userListenTbList = userListenTbService.findByUserId(userId);
        Integer total = 0;
        for (UserListenTb userListenTb : userListenTbList) {
            total += userListenTb.getPlayNumber();
        }
        songListPageVO.setPlayNumber(total);
        songListPageVO.setSongTotal(favorSongTbList.size());
        songListPageVO.setCreateTime(DateUtil.dateToString(userTb.getCreateTime()));
//        songListPageVO.setHeadImg("default");
        for (FavorSongTb favorSongTb : favorSongTbList) {
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTbService.findBySongId(favorSongTb.getSongId()), songTbVO);
            songTbVOList.add(songTbVO);
        }
        songListPageVO.setSongListSongs(songTbVOList);
        myMusicPageVO.setMySongList(songListPageVO);

        List<SongListTb> songListTbList = songListTbService.findByUserId(userId);
        myMusicPageVO.setCreateSongListTotal(songListTbList.size());
        myMusicPageVO.setCreateSongList(toSongListVO(songListTbList));

        List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findByUserId(userId);
        myMusicPageVO.setCollectSongListTotal(collectSongListTbList.size());
        List<SongListTb> songListTbList1 = new ArrayList<>();
        for (CollectSongListTb collectSongListTb : collectSongListTbList) {
            songListTbList1.add(songListTbService.findBySongListId(collectSongListTb.getSongListId()));
        }
        myMusicPageVO.setCreateSongList(toSongListVO(songListTbList1));

        //喜欢的歌手总数
        List<FavorSingerTb> favorSingerTbList = favorSingerTbService.findByUserId(userId);
        myMusicPageVO.setSingerTotal(favorSingerTbList.size());

        return ResultVOUtil.success(myMusicPageVO);
    }

    private List<SongListTbVO> toSongListVO(List<SongListTb> songListTbList) {
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        for (SongListTb songListTb : songListTbList) {
            SongListTbVO songListTbVO = new SongListTbVO();
            BeanUtils.copyProperties(songListTb, songListTbVO);
            songListTbVO.setSongTotal(songListSongTbService.findBySongListId(songListTb.getSongListId()).size() + "首");
            songListTbVOList.add(songListTbVO);
        }
        return songListTbVOList;
    }

    //TODO 用户创建歌单详情页
    @ApiOperation(value = "用户创建的歌单详情",notes = "在url中输入歌单id查询")
    @GetMapping(value = "/mysonglist/createlist")
    public ResultVO createList(HttpServletRequest request,
                               @RequestParam("songListId") Integer songListId) {

        return ResultVOUtil.success(getSongListPageVO(songListId));
    }

    //用户收藏的歌单详情页
    @ApiOperation(value = "用户收藏的歌单详情",notes = "在url中输入歌单id查询")
    @GetMapping(value = "/mysonglist/collect")
    public ResultVO collectSongList(HttpServletRequest request,
                                    @RequestParam(value = "songListId") Integer songListId) {
        return ResultVOUtil.success(getSongListPageVO(songListId));

    }

    private SongListPageVO getSongListPageVO(Integer songListId) {
        SongListTb songListTb = songListTbService.findBySongListId(songListId);
        SongListPageVO songListPageVO = new SongListPageVO();
        BeanUtils.copyProperties(songListTb, songListPageVO);
        songListPageVO.setCreateTime(DateUtil.dateToString(songListTb.getCreateTime()));
        songListPageVO.setSongListSongs(getInclude(songListId, CommentTypeEnum.SONG_LIST_COMMENT.getCode()));
        songListPageVO.setSongListComments(getCommentPage(PAGEMINNUMBER, songListId, CommentTypeEnum.SONG_LIST_COMMENT.getCode()));
        return songListPageVO;
    }

}
