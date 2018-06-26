package com.musicweb.music.controller;


import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.*;
import com.musicweb.music.VO.SearchVO.SearchResultDataVO;
import com.musicweb.music.VO.SearchVO.SearchResultVO;
import com.musicweb.music.VO.SearchVO.SearchSongListResultVO;
import com.musicweb.music.VO.SearchVO.SearchUserResultVO;
import com.musicweb.music.entity.*;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.enums.SearchTypeEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.IntegerUtil;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "搜索功能")
@RestController
@RequestMapping(value = "/api")
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private MvTbServiceImpl mvTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    private static final Integer PAGEMINNUMBER = 1;

    private static final Integer PAGEMINSIZE = 3;

    private static final Integer PAGEMAXSIZE = 30;


    //搜索下拉框
    @ApiOperation(value = "搜索功能",notes = "在url中添加搜索内容")
    @GetMapping(value = "/search")
    public ResultVO search(@RequestParam("content") String content){
        PageInfo<SongTb> songTbPageInfo = songTbService.searchBySongName(content,PAGEMINNUMBER,PAGEMINSIZE);
        PageInfo<SingerTb> singerTbPageInfo = singerTbService.searchBySingerName(content,PAGEMINNUMBER,PAGEMINSIZE);
        PageInfo<AlbumTb> albumTbPageInfo = albumTbService.searchByAlbumName(content,PAGEMINNUMBER,PAGEMINSIZE);
        PageInfo<MvTb> mvTbPageInfo = mvTbService.searchByMvName(content,PAGEMINNUMBER,PAGEMINSIZE);
        PageInfo<SongListTb> songListTbPageInfo = songListTbService.searchBySongListName(content,PAGEMINNUMBER,PAGEMINSIZE);
        SearchResultVO searchResultVO = new SearchResultVO();

        searchResultVO.setSongData(handleResults(songTbPageInfo.getList()));
        searchResultVO.setSingerData(handleResults(singerTbPageInfo.getList()));
        searchResultVO.setAlbumData(handleResults(albumTbPageInfo.getList()));
        searchResultVO.setMvData(handleResults(mvTbPageInfo.getList()));
        searchResultVO.setSongListData(handleResults(songListTbPageInfo.getList()));


        return ResultVOUtil.success(searchResultVO);
    }

    //完全搜索
    @ApiOperation(value = "搜索页",notes = "在url中添加搜索内容、搜索的类型（1001：歌曲，1002：歌手，1003：专辑，1004：歌词，1005：歌单，1006：用户  默认为1001）、页数")
    @GetMapping(value = "/searchall")
    public ResultVO searchAll(@RequestParam("content") String content,
                              @RequestParam(value = "type",defaultValue = "1001") String typeNum,
                              @RequestParam(value = "pageNumber",defaultValue = "1") String pageNum) {
        Map<String, Integer> idMap = new HashMap<>();
        Map<String, String> contentMap = new HashMap<>();
        Integer pageNumber = Integer.valueOf(pageNum);
        Integer typeNumber = Integer.valueOf(typeNum);

        switch (typeNumber) {
            case 1001: {
                PageInfo<SongTb> songTbPageInfo = songTbService.searchBySongName(content, pageNumber, PAGEMAXSIZE);
                List<SongTbVO> songTbVOList = handleSongResult(songTbPageInfo, 0);
                PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbVOList);

                return ResultVOUtil.success(SearchTypeEnum.SONG.getCode(),SearchTypeEnum.SONG.getMsg(),pageResultVO);
            }
            case 1002: {
                PageInfo<SingerTb> singerTbPageInfo = singerTbService.searchBySingerName(content,pageNumber,PAGEMAXSIZE);
                List<SingerTb> singerTbList = singerTbPageInfo.getList();
                List<SingerTbVO> singerTbVOList = new ArrayList<>();
                for (SingerTb singerTb:singerTbList){
                    SingerTbVO singerTbVO = new SingerTbVO();
                    BeanUtils.copyProperties(singerTb,singerTbVO);
                    singerTbVOList.add(singerTbVO);
                }
                PageResultVO pageResultVO = new PageResultVO(singerTbPageInfo.getPages(),singerTbPageInfo.getTotal(),singerTbPageInfo.getPageNum(),singerTbPageInfo.getSize(),singerTbVOList);
                return ResultVOUtil.success(SearchTypeEnum.SINGER.getCode(),SearchTypeEnum.SINGER.getMsg(),pageResultVO);
            }
            case 1003: {
                PageInfo<AlbumTb> albumTbPageInfo = albumTbService.searchByAlbumName(content,pageNumber,PAGEMAXSIZE);
                List<AlbumTb> albumTbList = albumTbPageInfo.getList();
                List<AlbumTbVO> albumTbVOList = new ArrayList<>();
                for (AlbumTb albumTb:albumTbList){
                    AlbumTbVO albumTbVO = new AlbumTbVO();
                    BeanUtils.copyProperties(albumTb,albumTbVO);
//                    albumTbVO.setSingerName(singerTbService.findBySingerId(albumTb.getSingerId()).getSingerName());
                    albumTbVOList.add(albumTbVO);
                }
                PageResultVO pageResultVO = new PageResultVO(albumTbPageInfo.getPages(),albumTbPageInfo.getTotal(),albumTbPageInfo.getPageNum(),albumTbPageInfo.getSize(),albumTbVOList);
                return ResultVOUtil.success(SearchTypeEnum.ALBUM.getCode(),SearchTypeEnum.ALBUM.getMsg(),pageResultVO);
            }
            case 1004: {
                PageInfo<SongTb> songTbPageInfo = songTbService.searchByLyric(content, pageNumber, PAGEMAXSIZE);
                List<SongTbVO> songTbVOList = handleSongResult(songTbPageInfo, 1);
                PageResultVO pageResultVO = new PageResultVO(songTbPageInfo.getPages(),songTbPageInfo.getTotal(),songTbPageInfo.getPageNum(),songTbPageInfo.getSize(),songTbVOList);
                return ResultVOUtil.success(SearchTypeEnum.LYRIC.getCode(),SearchTypeEnum.LYRIC.getMsg(),pageResultVO);
            }
            case 1005: {
                PageInfo<SongListTb> songListTbPageInfo = songListTbService.searchBySongListName(content,pageNumber,PAGEMAXSIZE);
                List<SongListTb> songListTbList = songListTbPageInfo.getList();
                List<SearchSongListResultVO> searchSongListResultVOList = new ArrayList<>();
                for (SongListTb songListTb:songListTbList){
                    SearchSongListResultVO searchSongListResultVO = new SearchSongListResultVO();
                    BeanUtils.copyProperties(songListTb,searchSongListResultVO);
                    searchSongListResultVO.setUserNickname(userTbService.findById(songListTb.getUserId()).getUserNickname());
                    searchSongListResultVO.setCollectNumber(IntegerUtil.to(songListTb.getCollectNumber()));
                    searchSongListResultVO.setPlayNumber(IntegerUtil.to(songListTb.getPlayNumber()));
                    searchSongListResultVO.setSongAllNumber(IntegerUtil.to(songListSongTbService.findBySongListId(songListTb.getSongListId()).size()));
                    searchSongListResultVOList.add(searchSongListResultVO);
                }
                PageResultVO pageResultVO = new PageResultVO(songListTbPageInfo.getPages(),songListTbPageInfo.getTotal(),songListTbPageInfo.getPageNum(),songListTbPageInfo.getSize(),searchSongListResultVOList);
                return ResultVOUtil.success(SearchTypeEnum.SONG_LIST.getCode(),SearchTypeEnum.SONG_LIST.getMsg(),pageResultVO);
            }
            case 1006: {
                PageInfo<UserTb> userTbPageInfo = userTbService.searchByUserNickname(content,pageNumber,PAGEMAXSIZE);
                List<UserTb> userTbList = userTbPageInfo.getList();
                List<SearchUserResultVO> searchUserResultVOList = new ArrayList<>();
                for (UserTb userTb: userTbList){
                    SearchUserResultVO searchUserResultVO = new SearchUserResultVO();
                    BeanUtils.copyProperties(userTb,searchUserResultVO);
                    List<SongListTb> songListTbList = songListTbService.findByUserId(userTb.getUserId());
                    searchUserResultVO.setSongListNumber(songListTbList.size());
                    searchUserResultVO.setFanNumber(IntegerUtil.to(userTb.getFanNumber()));
                    searchUserResultVOList.add(searchUserResultVO);
                }
                PageResultVO pageResultVO = new PageResultVO(userTbPageInfo.getPages(),userTbPageInfo.getTotal(),userTbPageInfo.getPageNum(),userTbPageInfo.getSize(),searchUserResultVOList);
                return ResultVOUtil.success(SearchTypeEnum.USER.getCode(),SearchTypeEnum.USER.getMsg(),pageResultVO);
            }
            default:
                throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }
    }

    private List<SongTbVO> handleSongResult(PageInfo<SongTb> songTbPageInfo,Integer n){

        try {
            List<SongTbVO> songTbVOList = new ArrayList<>();
            List<SongTb> songTbList = songTbPageInfo.getList();
            for (SongTb songTb: songTbList){
                SongTbVO songTbVO = new SongTbVO();
                BeanUtils.copyProperties(songTb,songTbVO);
//                songTbVO.setSingerName(singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
//                songTbVO.setAlbumName(albumTbService.findByAlbumId(songTb.getAlbumId()).getAlbumName());
                if (n == 0) songTbVO.setLyric(null);
                songTbVOList.add(songTbVO);
            }
            return songTbVOList;
        }catch (Exception e){
            throw new MusicException(ExceptionEnum.DATA_NULL);
        }
    }

    private List<SearchResultDataVO> handleResults(List<?> objects){
        try {
            List<SearchResultDataVO> searchResultDataVOList = new ArrayList<>();
            for (int i = 0; i < objects.size();i++){
                SearchResultDataVO searchResultDataVO = new SearchResultDataVO();
                if (objects.get(i) instanceof SongTb){
//                logger.info(((SongTb) objects.get(i)).toString());
                    SongTb songTb = (SongTb) objects.get(i);
                    searchResultDataVO.setId(songTb.getSongId());
                    searchResultDataVO.setContent((songTb.getSongName()) + "-" + singerTbService.findBySingerId(songTb.getSingerId()).getSingerName());
                    searchResultDataVOList.add(searchResultDataVO);
                }else if (objects.get(i) instanceof SingerTb){
                    SingerTb singerTb = (SingerTb) objects.get(i);
                    searchResultDataVO.setId(singerTb.getSingerId());
                    searchResultDataVO.setContent(singerTb.getSingerName());
                    searchResultDataVOList.add(searchResultDataVO);
                }else if (objects.get(i) instanceof AlbumTb){
                    AlbumTb albumTb = (AlbumTb) objects.get(i);
                    searchResultDataVO.setId(albumTb.getAlbumId());
                    searchResultDataVO.setContent(albumTb.getAlbumName() + "-" + singerTbService.findBySingerId(albumTb.getSingerId()).getSingerName());
                    searchResultDataVOList.add(searchResultDataVO);
                }else if (objects.get(i) instanceof MvTb){
                    MvTb mvTb = (MvTb) objects.get(i);
                    searchResultDataVO.setId(mvTb.getMvId());
                    searchResultDataVO.setContent("MV:" + mvTb.getMvName() + "-" + singerTbService.findBySingerId(mvTb.getSingerId()).getSingerName());
                    searchResultDataVOList.add(searchResultDataVO);
                }else if (objects.get(i) instanceof SongListTb){
                    SongListTb songListTb = (SongListTb)objects.get(i);
                    searchResultDataVO.setId(songListTb.getSongListId());
                    searchResultDataVO.setContent(songListTb.getSongListName());
                    searchResultDataVOList.add(searchResultDataVO);
                }
            }
            return searchResultDataVOList;
        }catch (Exception e){
            throw new MusicException(ExceptionEnum.DATA_NULL);
        }

    }

//    private List<SearchResultDataVO> handleResult(List<Object> objectList){
//        List<SearchResultDataVO> searchResultDataVOList = new ArrayList<>();
//        for (Object object: objectList){
//            SearchResultDataVO searchResultDataVO = new SearchResultDataVO();
//            if (object instanceof SongTb){
//                searchResultDataVO.setId(((SongTb) object).getSongId());
//                searchResultDataVO.setContent(((SongTb) object).getSongName()+"-"+singerTbService.findBySingerId(((SongTb) object).getSingerId()).getSingerName());
//                searchResultDataVOList.add(searchResultDataVO);
//            }
//        }
//        return searchResultDataVOList;
//    }
//
//    private List<Object> changeObject(List<?> tList){
//        List<Object> objectList = new ArrayList<>();
//        for (int i = 0; i<objectList.size();i++){
//            objectList.add(objectList.get(i));
//        }
//        return objectList;
//    }
}
