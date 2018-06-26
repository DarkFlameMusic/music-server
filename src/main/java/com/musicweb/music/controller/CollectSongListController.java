package com.musicweb.music.controller;


import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.SongListTbVO;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.entity.collecttable.CollectSongListTb;
import com.musicweb.music.service.impl.CollectSongListTbServiceImpl;
import com.musicweb.music.service.impl.SongListTbServiceImpl;
import com.musicweb.music.service.impl.UserTbServiceImpl;
import com.musicweb.music.utils.IntegerUtil;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class CollectSongListController {

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private UserTbServiceImpl userTbService;

    //TODO 未测试 用户收藏的歌单
    @ApiOperation(value = "获取用户收藏的歌单",notes = "在url中添加用户id获取")
    @GetMapping(value = "/storeSonglist")
    public ResultVO storeSongList(@RequestParam("userId") Integer userId){

        List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findByUserId(userId);
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        //TODO 待修改
        for(CollectSongListTb collectSongListTb: collectSongListTbList){
            SongListTbVO songListTbVO = new SongListTbVO();
            UserTb userTb = userTbService.findById(collectSongListTb.getUserId());
            SongListTb songListTb = songListTbService.findBySongListId(collectSongListTb.getSongListId());
            songListTbVO.setSongListName(songListTb.getSongListName());
            songListTbVO.setSongListId(songListTb.getSongListId());
            songListTbVO.setUserId(userTb.getUserId());
            songListTbVO.setUserNickname(userTb.getUserNickname());
            songListTbVO.setLabel(songListTb.getLabel());
            songListTbVO.setSongListImg(songListTb.getSongListImg());
            songListTbVO.setPlayNumber(IntegerUtil.to(songListTb.getPlayNumber()));
            songListTbVOList.add(songListTbVO);
        }
        return ResultVOUtil.success(songListTbVOList);
    }
}
