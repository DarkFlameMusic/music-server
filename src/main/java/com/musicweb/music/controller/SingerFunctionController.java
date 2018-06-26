package com.musicweb.music.controller;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.musicweb.music.VO.PageResultVO;
import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.SongTbVO;
import com.musicweb.music.entity.SongListTb;
import com.musicweb.music.entity.SongTb;
import com.musicweb.music.service.impl.AlbumTbServiceImpl;
import com.musicweb.music.service.impl.SingerTbServiceImpl;
import com.musicweb.music.service.impl.SongTbServiceImpl;
import com.musicweb.music.utils.CookieUtil;
import com.musicweb.music.utils.ResultVOUtil;
import com.musicweb.music.utils.TokenUtil;
import com.musicweb.music.utils.UploadUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping(value = "/api/singer")
public class SingerFunctionController {
    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    //发布歌曲1.上传歌曲2.添加入数据库
    //TODO
    @PostMapping("/upload")
    public ResultVO uploadSong(HttpServletRequest request,
                               @RequestParam("songName") String songName,
                               @RequestParam("songTime") String songTime,
                               @RequestParam("language") String language,
                               @RequestParam("albumId") Integer albumId,
                               @RequestParam("singStyle") String singStyle,
                               @RequestParam("mvId") Integer mvId,
                               @RequestParam("songFile") MultipartFile songFile,
                               @RequestParam("songLyric") MultipartFile songLyric) throws IOException {

        Claims claims = TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        FileInputStream fileInputStream = (FileInputStream) songFile.getInputStream();
        String songUrl = UploadUtil.commonUpload(fileInputStream,songFile.getOriginalFilename());

        return ResultVOUtil.success();
    }

    //TODO 未测试 查询个人发布的歌曲
    @GetMapping("/get/song")
    public ResultVO getSingerSong(HttpServletRequest request,
                                  @RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam(value = "pageSize",defaultValue = "20") String pageSize) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        PageInfo<SongTb> songTbPageInfo = songTbService.findBySingerId(singerTbService.findByUserId(userId).getSingerId(), pageNumber, Integer.valueOf(pageSize));
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb:songTbPageInfo.getList()){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbPageInfo.getList());
        return ResultVOUtil.success(pageNumber);
    }
    //TODO 未测试通过歌名查找
    @GetMapping("/search")
    public ResultVO searchSong(@PathVariable("content") String songName,
                               @RequestParam(value = "pageNumber",defaultValue = "1") String pageNumber,
                               @RequestParam(value = "pageSize",defaultValue = "20") String pageSize){
        PageInfo<SongTb> songTbPageInfo = songTbService.searchBySongName(songName,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb:songTbPageInfo.getList()){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbPageInfo.getList());
        return ResultVOUtil.success(pageResultVO);
    }
    //编辑个人发布的歌曲
    @PostMapping("/update")
    public ResultVO updateSong(SongTb songTb){
        return ResultVOUtil.success();
    }
    //发布专辑
    //编辑个人发布的专辑
}
