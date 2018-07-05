package com.musicweb.music.controller;


import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.*;
import com.musicweb.music.entity.*;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SongListController extends BasePageController{

    private static final Logger logger = LoggerFactory.getLogger(SongListController.class);

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private SongListCommentTbServiceImpl songListCommentTbService;

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private CommentTbServiceImpl commentTbService;

    private static final Integer PAGEMINNUMBER = 1;;

    private static final Integer PAGESIZE=20;

    private static final Integer PAGEMAXSIZE=35;

    private static final Integer MAXHOTRECOMMOND = 8;

    private static final Integer MAXPERSONRECOMMOND = 4;

    private static final Integer MINADMIRENUMBER = 100;

    //热门歌单
    @ApiOperation(value = "热门歌单")
    @GetMapping(value = "/hotRecommond")
    public ResultVO hotRecommond(){
        List<SongListTb> songListTbList = songListTbService.findAllSortPlayNumber();
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        for(int i = 0;i < MAXHOTRECOMMOND;i++){
            if (songListTbList.size()<MAXHOTRECOMMOND){
                throw new MusicException(ExceptionEnum.LIST_OVERFLOW);
            }
            SongListTbVO songListTbVO = new SongListTbVO();
            BeanUtils.copyProperties(songListTbList.get(i),songListTbVO);
            songListTbVO.setUserNickname(userTbService.findById(songListTbList.get(i).getUserId()).getUserNickname());
            songListTbVOList.add(songListTbVO);
        }
        return ResultVOUtil.success(songListTbVOList);
    }

    //个性化推荐歌单
    @ApiOperation(value = "个性化推荐歌单")
    @GetMapping(value = "/personRecommond")
    public ResultVO personRecommond(){
        List<SongListTb> songListTbList = songListTbService.findAllSortPlayNumber();
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        for(int i = 0;i < MAXPERSONRECOMMOND;i++){
            if (songListTbList.size()<MAXPERSONRECOMMOND){
                throw new MusicException(ExceptionEnum.LIST_OVERFLOW);
            }
            SongListTbVO songListTbVO = new SongListTbVO();
            BeanUtils.copyProperties(songListTbList.get(i),songListTbVO);
            songListTbVO.setUserNickname(userTbService.findById(songListTbList.get(i).getUserId()).getUserNickname());
            songListTbVOList.add(songListTbVO);
        }
        return ResultVOUtil.success(songListTbVOList);
    }

    //相关推荐
//    @GetMapping(value = "/related")
//    public ResultVO related(@RequestParam(value = "n") Integer n,
//                            @RequestParam(value = "label") String  label){
//
//        List<SongListTb> songListTbList = songListTbService.findByLabel(label);
//        List<SongListTbRelatedVO> songListTbRelatedVOList = new ArrayList<>();
//        for(int i = 0;i<n;i++){
//            SongListTbRelatedVO songListTbRelatedVO = new SongListTbRelatedVO();
//            songListTbRelatedVO.setSongListName(songListTbList.get(i).getSongListName());
//            songListTbRelatedVO.setUserNickname(userTbService.findById(songListTbList.get(i).getUserId()).getUserNickname());
//            songListTbRelatedVOList.add(songListTbRelatedVO);
//        }
//        return ResultVOUtil.success(songListTbRelatedVOList);
//    }
    //歌单页所有数据
    @ApiOperation(value = "歌单页")
    @GetMapping(value = "/songlistpage")
    public ResultVO songListPage(@RequestParam("songListId") Integer songListId){
        SongListTb songListTb = songListTbService.findBySongListId(songListId);
        UserTb userTb = userTbService.findById(songListTb.getUserId());
//        List<SongListSongTb> songListSongTbList = songListSongTbService.findBySongListId(songListId);
//        List<SongListCommentTb> songListCommentTbList = songListCommentTbService.findBySongListId(songListId);
//        List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findBySongListId(songListId);
//        List<SongListTb> songListTbList = songListTbService.findByLabel(songListTb.getLabel());
        List<SongListTbRelatedVO> songListTbRelatedVOList = getSimilar(songListTb.getLabel(),CommentTypeEnum.SONG_LIST_COMMENT.getCode());
        List<UserCollectSongListVO> userCollectSongListVOList = getLiker(songListId,CommentTypeEnum.SONG_LIST_COMMENT.getCode());
//        List<SongListCommentDataVO> songListCommentDataVOList = new ArrayList<>();
        List<SongTbVO> songTbVOList = getInclude(songListId,CommentTypeEnum.SONG_LIST_COMMENT.getCode());
        SongListPageVO songListPageVO = new SongListPageVO();
//        SongListCommentVO songListCommentVO = new SongListCommentVO();
        BeanUtils.copyProperties(songListPageVO,userTb);
        BeanUtils.copyProperties(songListTb,songListPageVO);
        songListPageVO.setCreateTime(DateUtil.dateToString(songListTb.getCreateTime()));
//        songListPageVO.setUserNickname(userTb.getUserNickname());
//        songListPageVO.setHeadImg(userTb.getHeadImg());
        songListPageVO.setSongTotal(songTbVOList.size());


//        for(SongListSongTb songListSongTb: songListSongTbList){
//            SongTb songTb = new SongTb();
//            SongTbVO songTbVO = new SongTbVO();
//            songTb = songTbService.findBySongId(songListSongTb.getSongId());
//            BeanUtils.copyProperties(songTb,songTbVO);
//            songTbVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
//            songTbVO.setAlbumName(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumName());
//            songTbVO.setLyric(null);
//            songTbVOList.add(songTbVO);
//        }

        //精彩评论 MAX10
        List<CommentTb> commentTbList = commentTbService.findSortAdmireNumber(songListId,CommentTypeEnum.SONG_LIST_COMMENT.getCode(),MINADMIRENUMBER);
        List<CommentResultVO> commentResultVOList = handleComment(commentTbList);
//        for (CommentResultVO commentResultVO: commentResultVOList){
//            logger.info(commentResultVO.toString());
//        }



        //普通评论 MAX20
//        for(SongListCommentTb songListCommentTb: songListCommentTbList){
//            SongListCommentDataVO songListCommentDataVO = new SongListCommentDataVO();
//            BeanUtils.copyProperties(songListCommentTb,songListCommentDataVO);
//            songListCommentDataVO.setCreateTime(DateUtil.dateToString(songListCommentTb.getCreateTime()));
//            songListCommentDataVO.setUserName(userTbService.findById(songListCommentTb.getUserId()).getUserNickname());
//            songListCommentDataVO.setHeadImg(userTbService.findById(songListCommentTb.getUserId()).getHeadImg());
//            songListCommentDataVOList.add(songListCommentDataVO);
//        }

        ResultVO resultVO = getCommentPage(PAGEMINNUMBER,songListId,CommentTypeEnum.SONG_LIST_COMMENT.getCode());



//        for(CollectSongListTb collectSongListTb: collectSongListTbList){
//            UserCollectSongListVO userCollectSongListVO = new UserCollectSongListVO();
//            BeanUtils.copyProperties(collectSongListTb,userCollectSongListVO);
//            userCollectSongListVO.setHeadImg(userTbService.findById(collectSongListTb.getUserId()).getHeadImg());
//            userCollectSongListVOList.add(userCollectSongListVO);
//        }

//        for(SongListTb songListTb1: songListTbList){
//            SongListTbRelatedVO songListTbRelatedVO = new SongListTbRelatedVO();
//            BeanUtils.copyProperties(songListTb1,songListTbRelatedVO);
//            songListTbRelatedVO.setUserNickname(userTbService.findById(songListTb1.getUserId()).getUserNickname());
//            songListTbRelatedVOList.add(songListTbRelatedVO);
//        }


        songListPageVO.setSongListSongs(songTbVOList);


//        songListCommentVO.setCommentNumber(songListCommentDataVOList.size());
//        songListCommentVO.setDataList(songListCommentDataVOList);
        songListPageVO.setSongListGoodComment(commentResultVOList);

        songListPageVO.setSongListComments(resultVO);

        songListPageVO.setSongListCollect(userCollectSongListVOList);

        songListPageVO.setSimilaritySongList(songListTbRelatedVOList);

        return ResultVOUtil.success(songListPageVO);
    }

    //所有歌单
    //按照分类搜索
    @ApiOperation(value = "所有歌单",notes = "按分类查找")
    @GetMapping(value = "/allsonglist")
    public ResultVO searchAllSongList(@RequestParam(name = "content",defaultValue = "666") String content,
                                      @RequestParam(name = "order",defaultValue = "hot") String order){
        PageResultVO pageResultVO = null;
        if ((content.equals("666") && order.equals("hot")) || (content.equals("666") && order.equals("new"))){
            PageInfo<SongListTb> songListTbPageInfo = songListTbService.searchByLabel(PAGEMINNUMBER,PAGEMAXSIZE,order);
            List<SongListTbVO> songListTbVOList = handleAllsongList(songListTbPageInfo);
            pageResultVO = new PageResultVO(songListTbPageInfo.getPages(),songListTbPageInfo.getTotal(),songListTbPageInfo.getPageNum(),songListTbPageInfo.getSize(),songListTbVOList);
        }else if ((!content.equals("666")&& order.equals("hot")) || (!content.equals("666") && order.equals("new"))){
            PageInfo<SongListTb> songListTbPageInfo = songListTbService.searchByLabel(content,PAGEMINNUMBER,PAGEMAXSIZE,order);
            List<SongListTbVO> songListTbVOList = handleAllsongList(songListTbPageInfo);
            pageResultVO = new PageResultVO(songListTbPageInfo.getPages(),songListTbPageInfo.getTotal(),songListTbPageInfo.getPageNum(),songListTbPageInfo.getSize(),songListTbVOList);
        }else {
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }
        return ResultVOUtil.success(pageResultVO);
    }
    private List<SongListTbVO> handleAllsongList(PageInfo<SongListTb> songListTbPageInfo){
        List<SongListTbVO> songListTbVOList = new ArrayList<>();
        for (SongListTb songListTb:songListTbPageInfo.getList()){
            SongListTbVO songListTbVO = new SongListTbVO();
            BeanUtils.copyProperties(songListTb,songListTbVO);
            songListTbVOList.add(songListTbVO);
        }
        return songListTbVOList;
    }

    //排行榜
    //新歌榜
    //热歌榜
    @ApiOperation(value = "排行榜",notes = "在url中添加hot或new")
    @GetMapping(value = "/toplist")
    public ResultVO newSong(@RequestParam(value = "content",defaultValue = "hot") String content){

        List<SongTbVO> songTbVOList = null;
        List<SongTb> songTbList = null;
        if (content.equals("hot")){
            songTbList=songTbService.findSortPlayNumber();
            songTbVOList = handleSong(songTbList);
        }else if (content.equals("new")){
            songTbList = songTbService.findSortCreateTime();
            songTbVOList = handleSong(songTbList);
        }else {
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }

        return ResultVOUtil.success(songTbVOList);
    }

    private  List<SongTbVO> handleSong(List<SongTb> songTbList){
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb:songTbList){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
            songTbVOList.add(songTbVO);
        }
        return songTbVOList;
    }


    //更新歌单
    @ApiOperation(value = "更新歌单")
    @PostMapping("/update/songlist")
    public ResultVO adminUpdateSongList(@RequestParam("songListId") Integer songListId,
                                        @RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                        @RequestParam(value = "songListName",required = false) String songListName,
                                        @RequestParam(value = "label",required = false) String label) throws IOException {
        SongListTb songListTb = songListTbService.findBySongListId(songListId);
        if(multipartFile!=null){
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            songListTb.setSongListImg(UploadUtil.commonUpload(fileInputStream,multipartFile.getOriginalFilename()));
        }
        songListTb.setSongListName(songListName);
        songListTb.setLabel(label);
        songListTbService.updateOne(songListTb);
        return ResultVOUtil.success();
    }
    //歌单添加歌曲
    @ApiOperation(value = "歌单添加歌曲")
    @PostMapping("/insert/songlist/song")
    public ResultVO adminInsertSongListSong(@RequestParam("songListId") Integer songListId,
                                            @RequestParam("songId") Integer songId){
        SongListSongTb songListSongTb = new SongListSongTb();
        songListSongTb.setSongListId(songListId);
        songListSongTb.setSongId(songId);
        songListSongTb.setCreateTime(new Date());
        songListSongTbService.insertOne(songListSongTb);
        return ResultVOUtil.success();
    }

    //歌单删除歌曲
    @ApiOperation(value = "歌单删除歌曲")
    @PostMapping("/delete/songlist/song")
    public ResultVO adminDeleteSongListSong(@RequestParam("songListId") Integer songListId,
                                            @RequestParam("songId") Integer songId){
        SongListSongTb songListSongTb = new SongListSongTb();
        songListSongTb.setSongListId(songListId);
        songListSongTb.setSongId(songId);
        songListSongTb.setCreateTime(new Date());
        songListSongTbService.deleteBySongListSongId(songListSongTbService.findBySongListIdAndSongId(songListId,songId).getSongListSongId());
        return ResultVOUtil.success();
    }

    //创建歌单
    @ApiOperation(value = "创建歌单")
    @PostMapping(value = "/createsonglist")
    public ResultVO createSongList(HttpServletRequest request,
                                   @RequestParam(value = "songListName") String songListName,
                                   @RequestParam(value = "labels",required = false) String labels,
                                   @RequestParam(value = "intro",required = false) String intro,
                                   @RequestParam(value = "image",required = false) MultipartFile imageFile) throws IOException {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        SongListTb songListTb = new SongListTb();
        songListTb.setSongListName(songListName);
        songListTb.setCreateTime(new Date());
        songListTb.setUserId(userId);
        if(labels!=null){
            songListTb.setLabel(labels);
        }else {
            songListTb.setLabel("");
        }
        if(intro!=null){
            songListTb.setSongListIntro(intro);
        }else {
            songListTb.setSongListIntro("");
        }
        if(imageFile!=null){
            FileInputStream fileInputStream = (FileInputStream) imageFile.getInputStream();
            songListTb.setSongListImg(UploadUtil.commonUpload(fileInputStream,imageFile.getOriginalFilename()));
        }else {
            songListTb.setSongListImg("http://p1.music.126.net/tGHU62DTszbFQ37W9qPHcg==/2002210674180197.jpg?param=200y200");
        }

        songListTbService.insertOne(songListTb);
        songListTb = songListTbService.findBySongListId(songListTb.getSongListId());
        return ResultVOUtil.success(0, "创建成功", songListTb);
    }


    //删除歌单
    @ApiOperation(value = "删除歌单")
    @PostMapping(value = "/deletesonglist")
    public ResultVO deleteSongList(HttpServletRequest request,
                                   @RequestParam("songListId") Integer songListId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        songListTbService.deleteOne(songListId);
        return ResultVOUtil.success(0, "删除成功", null);
    }

}
