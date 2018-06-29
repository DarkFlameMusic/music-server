package com.musicweb.music.controller;


import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.SongListTbCreateVO;
import com.musicweb.music.VO.UserTbVO;
import com.musicweb.music.entity.MailSendTo;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.enums.GenderEnum;
import com.musicweb.music.enums.UserJurisdictionEnum;
import com.musicweb.music.service.impl.MailServiceImpl;
import com.musicweb.music.service.impl.SongListTbServiceImpl;
import com.musicweb.music.service.impl.UserTbServiceImpl;
import com.musicweb.music.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;


    @ApiOperation(value = "查询所有用户",notes = "查询所有用户信息")
    //返回所有用户
    @GetMapping(value = "/loginUser")
    public ResultVO loginUser() {
        List<UserTb> userList = userTbService.findAll();
        List<UserTbVO> userTbVOList = new ArrayList<>();

        for (UserTb userTb : userList) {
            UserTbVO userTbVO = new UserTbVO();
            BeanUtils.copyProperties(userTb, userTbVO);
            userTbVO.setBirthDate(DateUtil.dateToString(userTb.getBirthDate()));
            userTbVOList.add(userTbVO);
        }

        return ResultVOUtil.success(userTbVOList);
    }


    //用户创建的歌单
    @ApiOperation(value = "用户创建的歌单",notes = "通过用户id查找")
    @GetMapping(value = "/user/createSonglist")
    public ResultVO userCreateSongList(@RequestParam(name = "userId") Integer userId) {

        List<SongListTb> songListTbList = songListTbService.findByUserId(userId);
        List<SongListTbCreateVO> songListTbCreateVOList = new ArrayList<>();
        for (SongListTb songListTb : songListTbList) {
            SongListTbCreateVO songListTbCreateVO = new SongListTbCreateVO();
            BeanUtils.copyProperties(songListTb, songListTbCreateVO);
            songListTbCreateVOList.add(songListTbCreateVO);
        }

        return ResultVOUtil.success(songListTbCreateVOList);
    }

    //通过token获取用户信息
    @GetMapping("/get/user/info")
    public ResultVO getUserInfo(HttpServletRequest request){
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue());

        Integer userId = Integer.valueOf(claims.getId());

        UserTb userTb = userTbService.findById(userId);

        UserTbVO userTbVO = new UserTbVO();

        BeanUtils.copyProperties(userTb,userTbVO);
        String[] strings = {UserJurisdictionEnum.getSting(userTb.getJurisdiction())};

        userTbVO.setRoles(strings);

        return ResultVOUtil.success(userTbVO);
    }

    //更改用户权限
    @ApiOperation(value = "更改用户权限")
    @PostMapping(value = "/user/updateStatus")
    public ResultVO updateUsesrStatus(@RequestParam(name = "userId") Integer userId,
                                      @RequestParam(name = "status") Integer status) {

        UserTb userTb = userTbService.findById(userId);
        userTb.setJurisdiction(status);
        UserTb result = userTbService.updateUserTb(userTb);

        return ResultVOUtil.success(result);
    }

}
