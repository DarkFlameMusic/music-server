package com.musicweb.music.controller;

import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.UserListenTbVO;
import com.musicweb.music.entity.SongTb;
import com.musicweb.music.entity.UserListenTb;
import com.musicweb.music.service.impl.SingerTbServiceImpl;
import com.musicweb.music.service.impl.SongTbServiceImpl;
import com.musicweb.music.service.impl.UserListenTbServiceImpl;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(value = "/api")
public class UserListenController {

    @Autowired
    private UserListenTbServiceImpl userListenTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @ApiOperation(value = "用户播放次数榜单")
    @GetMapping(value = "/user/playRanking")
    public ResultVO userPlayRanking(@RequestParam(name = "userId") Integer userId){
        List<UserListenTb> userListenTbList = userListenTbService.findSortPlayNumber(userId);
        List<UserListenTbVO> userListenTbVOList = new ArrayList<>();
        for(UserListenTb userListenTb: userListenTbList){
            UserListenTbVO userListenTbVO = new UserListenTbVO();
            BeanUtils.copyProperties(userListenTb,userListenTbVO);
            SongTb songTb = songTbService.findBySongId(userListenTb.getSongId());
            userListenTbVO.setSongUrl(songTb.getSongUrl());
            userListenTbVO.setSongName(songTb.getSongName());
            userListenTbVO.setSingerId(songTb.getSingerId());
            userListenTbVO.setSingerNmae(singerTbService.findBySingerId(songTbService.findBySongId(userListenTb.getSongId()).getSingerId()).getSingerName());
            userListenTbVOList.add(userListenTbVO);
        }

        return ResultVOUtil.success(userListenTbVOList);
    }
}
