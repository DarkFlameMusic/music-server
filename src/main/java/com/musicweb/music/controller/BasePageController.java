package com.musicweb.music.controller;

import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.*;
import com.musicweb.music.entity.*;
import com.musicweb.music.entity.collecttable.CollectAlbumTb;
import com.musicweb.music.entity.collecttable.CollectSongListTb;
import com.musicweb.music.entity.favortable.FavorSingerTb;
import com.musicweb.music.entity.favortable.FavorSongTb;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.DateUtil;
import com.musicweb.music.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Controller
public class BasePageController<T> {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private CommentTbServiceImpl commentTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private FavorSongTbServiceImpl favorSongTbService;

    @Autowired
    private CollectAlbumTbServiceImpl collectAlbumTbService;

    public static final Integer PAGEMINNUMBER = 1;;

    private static final Integer PAGESIZE=20;

    public static final Integer PAGEMAXSIZE=35;

    private static final Integer MAXHOTRECOMMOND = 8;

    private static final Integer MAXPERSONRECOMMOND = 4;

    private static final Integer MINADMIRENUMBER = 100;

    private static final Integer LIKERSIZE = 8;

    private static final Integer SONGINCLUDESIZE = 3;

    private static final Integer SIMILARSIZE = 5;

    //所有评论
//    @ResponseBody
//    @PostMapping(value = "/api/commentpages")
    protected ResultVO getCommentPage(@RequestParam(value = "pageNumber") Integer pageNumber,
                                      @RequestParam("objectId") Integer objectId,
                                      @RequestParam("objectType") Integer objectType){
        PageInfo<CommentTb> commentTbPageInfo = commentTbService.findByPage(objectId,objectType,pageNumber,PAGESIZE);
        List<CommentTb> commentTbList = commentTbPageInfo.getList();
        List<CommentResultVO> commentResultVOList = handleComment(commentTbList);
        PageResultVO pageResultVO = new PageResultVO(commentTbPageInfo.getPages(),commentTbPageInfo.getTotal(),commentTbPageInfo.getPageNum(),commentTbPageInfo.getSize(),commentResultVOList);
        return ResultVOUtil.success(objectType, CommentTypeEnum.getString(objectType),pageResultVO);
    }

    protected List<UserCollectSongListVO> getLiker(Integer id, Integer n){

        if (n == CommentTypeEnum.SONG_COMMENT.getCode()){
            List<FavorSongTb> favorSongTbList = favorSongTbService.findBySongId(id);
            List<UserCollectSongListVO> userCollectSongListVOList = handleLiker(favorSongTbList);
            return userCollectSongListVOList;
        }else if (n == CommentTypeEnum.SONG_LIST_COMMENT.getCode()){
            List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findBySongListId(id);
            List<UserCollectSongListVO> userCollectSongListVOList = handleLiker(collectSongListTbList);
            return userCollectSongListVOList;
        }else if (n == CommentTypeEnum.ALBUM_COMMENT.getCode()){
            List<CollectAlbumTb> collectAlbumTbList = collectAlbumTbService.findByAlbumId(id);
            List<UserCollectSongListVO> userCollectSongListVOList = handleLiker(collectAlbumTbList);
            return userCollectSongListVOList;
        }else {
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }
    }
    private List<UserCollectSongListVO> handleLiker(List<?> list){
        List<UserCollectSongListVO> userCollectSongListVOList = new ArrayList<>();
        //TODO LIKERSIZE = 8
        for (int i = 0; i<list.size();i++){
            UserCollectSongListVO userCollectSongListVO = new UserCollectSongListVO();
            if (list.get(i) instanceof CollectSongListTb){
                CollectSongListTb collectSongListTb = (CollectSongListTb) list.get(i);
                BeanUtils.copyProperties(collectSongListTb,userCollectSongListVO);
                userCollectSongListVO.setHeadImg(userTbService.findById(collectSongListTb.getUserId()).getHeadImg());
                userCollectSongListVOList.add(userCollectSongListVO);
            }else if (list.get(i) instanceof CollectAlbumTb){
                CollectAlbumTb collectAlbumTb = (CollectAlbumTb) list.get(i);
                BeanUtils.copyProperties(collectAlbumTb,userCollectSongListVO);
                userCollectSongListVO.setHeadImg(userTbService.findById(collectAlbumTb.getUserId()).getHeadImg());
                userCollectSongListVOList.add(userCollectSongListVO);
            }else if (list.get(i) instanceof FavorSongTb){
                FavorSongTb favorSongTb = (FavorSongTb) list.get(i);
                BeanUtils.copyProperties(favorSongTb,userCollectSongListVO);
                userCollectSongListVO.setHeadImg(userTbService.findById(favorSongTb.getUserId()).getHeadImg());
                userCollectSongListVOList.add(userCollectSongListVO);
            }else{
                throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
            }
        }
        return userCollectSongListVOList;
    }

    protected List<?> getSimilar(String content, Integer typeNumber){
        if (typeNumber == CommentTypeEnum.SONG_COMMENT.getCode()){
            List<SongTb> songTbList = songTbService.findSortPlayNumber();

            //TODO
//            Random random = new Random();
//            Integer min = random.nextInt();
//            Integer max = min + SIMILARSIZE;
//            for (int i = min ; i<max;i++)

            List<SongTbVO> songTbVOList = copySongTbVO(songTbList);
            return songTbVOList;
        }else if (typeNumber == CommentTypeEnum.SONG_LIST_COMMENT.getCode()){
            List<SongListTb> songListTbList = songListTbService.findByLabel(content);
            List<SongListTbRelatedVO> songListTbRelatedVOList = new ArrayList<>();
            //TODO
            for(SongListTb songListTb1: songListTbList){
                SongListTbRelatedVO songListTbRelatedVO = new SongListTbRelatedVO();
                BeanUtils.copyProperties(songListTb1,songListTbRelatedVO);
                songListTbRelatedVO.setUserNickname(userTbService.findById(songListTb1.getUserId()).getUserNickname());
                songListTbRelatedVOList.add(songListTbRelatedVO);
            }
            return songListTbRelatedVOList;
        }else if (typeNumber == CommentTypeEnum.ALBUM_COMMENT.getCode()){
            List<AlbumTb> albumTbList = albumTbService.findByCompanyName(content);
            List<AlbumTbPageSimilarVO> albumTbPageSimilarVOList = new ArrayList<>();
            //TODO
//            for (int i = 0; i< SIMILARSIZE;i++)
            for (AlbumTb albumTb:albumTbList){
                AlbumTbPageSimilarVO albumTbPageSimilarVO = new AlbumTbPageSimilarVO();
                BeanUtils.copyProperties(albumTb,albumTbPageSimilarVO);
                albumTbPageSimilarVO.setIssueTime(DateUtil.dateToString(albumTb.getIssueTime()));
                albumTbPageSimilarVOList.add(albumTbPageSimilarVO);
            }
            return albumTbPageSimilarVOList;
        }else {
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }
    }

    protected List<?> getInclude(Integer id, Integer typeNumber){
        if (typeNumber == CommentTypeEnum.SONG_COMMENT.getCode()){
            List<SongListSongTb> songListSongTbList = songListSongTbService.findBySongId(id);
            List<SongListTbVO> songListTbVOList = new ArrayList<>();
            for (int i = 0;i<SONGINCLUDESIZE;i++){
                SongListTbVO songListTbVO = new SongListTbVO();
                SongListTb songListTb = songListTbService.findBySongListId(songListSongTbList.get(i).getSongListId());
                BeanUtils.copyProperties(songListTb,songListTbVO);
                songListTbVO.setUserNickname(userTbService.findById(songListTb.getUserId()).getUserNickname());
                songListTbVOList.add(songListTbVO);
            }
            return songListTbVOList;
        }else if (typeNumber == CommentTypeEnum.SONG_LIST_COMMENT.getCode()){
            List<SongListSongTb> songListSongTbList = songListSongTbService.findBySongListId(id);
            List<SongTbVO> songTbVOList = new ArrayList<>();
            for(SongListSongTb songListSongTb: songListSongTbList){
                SongTb songTb = new SongTb();
                SongTbVO songTbVO = new SongTbVO();
                songTb = songTbService.findBySongId(songListSongTb.getSongId());
                BeanUtils.copyProperties(songTb,songTbVO);
//                songTbVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
//                songTbVO.setAlbumName(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumName());
                songTbVO.setLyric(null);
                songTbVOList.add(songTbVO);
            }
            return songTbVOList;
        }else if (typeNumber == CommentTypeEnum.ALBUM_COMMENT.getCode()){
            List<SongTb> songTbList = songTbService.findByAlbumId(id);
            List<SongTbVO> songTbVOList = copySongTbVO(songTbList);
            return songTbVOList;
        }else {
            throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }

    }


    protected List<CommentResultVO> handleComment(List<CommentTb> commentTbList){
        if (commentTbList == null){
            return null;
        }
        List<CommentResultVO> commentResultVOList = new ArrayList<>();
        for (CommentTb commentTb: commentTbList){
            CommentResultVO commentResultVO = new CommentResultVO();
            UserTb userTb = userTbService.findById(commentTb.getUserId());
            BeanUtils.copyProperties(commentTb,commentResultVO);
            commentResultVO.setCreateTime(DateUtil.dateToString(commentTb.getCreateTime()));
            commentResultVO.setUserNickname(userTb.getUserNickname());
            commentResultVO.setHeadImg(userTb.getHeadImg());
            commentResultVOList.add(commentResultVO);
        }
        return commentResultVOList;
    }

    private  List<SongTbVO> copySongTbVO(List<SongTb> songTbList){
        List<SongTbVO> songTbVOList = new ArrayList<>();
        for (SongTb songTb: songTbList){
            SongTbVO songTbVO = new SongTbVO();
            BeanUtils.copyProperties(songTb,songTbVO);
//            songTbVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
            songTbVOList.add(songTbVO);
        }
        return songTbVOList;
    }


}
