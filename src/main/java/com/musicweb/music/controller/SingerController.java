package com.musicweb.music.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.*;
import com.musicweb.music.VO.adminVO.SingerSongVO;
import com.musicweb.music.VO.adminVO.SingerUserVO;
import com.musicweb.music.entity.*;
import com.musicweb.music.entity.favortable.FavorSingerTb;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.enums.SingerTypeEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.DateUtil;
import com.musicweb.music.utils.InitialUtil;
import com.musicweb.music.utils.ResultVOUtil;
import com.musicweb.music.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class SingerController {

    @Autowired
    private SingerTbServiceImpl singerTbService;

    @Autowired
    private FavorSingerTbServiceImpl favorSingerTbService;

    @Autowired
    private AlbumTbServiceImpl albumTbService;

    @Autowired
    private MvTbServiceImpl mvTbService;

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongTbServiceImpl songTbService;

    @Autowired
    private InitialUtil initialUtil;


    //首页入驻歌手
    @ApiOperation(value = "首页入住歌手")
    @GetMapping(value = "/indexSinger")
    public ResultVO indexSinger(@RequestParam(value = "n") Integer n) {

        List<SingerTb> singerTbList = singerTbService.findAllInSinger();
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i >= singerTbList.size()) {
                break;
            }
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTbList.get(i), singerTbVO);
            singerTbVOList.add(singerTbVO);
        }

        return ResultVOUtil.success(singerTbVOList);
    }

    //歌手列表
    @ApiOperation(value = "首页歌手列表")
    @GetMapping(value = "/singerList")
    public ResultVO singerList(@RequestParam(value = "n") Integer n) {
        List<SingerTb> singerTbList = singerTbService.findAll();
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTbList.get(i), singerTbVO);
            singerTbVOList.add(singerTbVO);
        }
        return ResultVOUtil.success(singerTbVOList);
    }


//    //TODO 热门歌手 未测试 待修改
//    @ApiOperation(value = "热门歌手")
//    @GetMapping(value = "/hotSinger")
//    public ResultVO hotSinger() {
//        Map<Integer, Integer> map = new HashMap<>();
//        List<FavorSingerTb> favorSingerTbList = favorSingerTbService.findAllBySingerId();
//        int n = 0;
//        for (int i = 0; i < favorSingerTbList.size(); i = n) {
//            for (int j = 0; j < favorSingerTbList.size(); j++) {
//                if (favorSingerTbList.get(i).getSingerId() == favorSingerTbList.get(j).getSingerId()) {
//                    n++;
//                }
//            }
//            map.put(favorSingerTbList.get(i).getSingerId(), n - i);
//        }
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
////        Collections.sort(list, (Comparator<Map.Entry<Integer, Integer>>) (o1, o2) -> { return o2.getValue().compareTo(o1.getValue());});
//        list.sort((Comparator<Map.Entry<Integer, Integer>>) (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//
//        //修改过的内容
//        List<SingerTb> singerTbList = singerTbService.findAll();
//        List<SingerTbVO> singerTbVOList = new ArrayList<>();
//        for (SingerTb singerTb : singerTbList) {
//            SingerTbVO singerTbVO = new SingerTbVO();
//            BeanUtils.copyProperties(singerTb, singerTbVO);
//            singerTbVOList.add(singerTbVO);
//        }
//
//        return ResultVOUtil.success(singerTbVOList);
//    }


    //推荐歌手
    @ApiOperation(value = "推荐歌手")
    @GetMapping(value = "/singer/singerList")
    public ResultVO artist() {
        //入驻歌手
        List<SingerTb> singerTbList = singerTbService.findAllInSinger();
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        for (int i = 0; i < singerTbList.size(); i++) {
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTbList.get(i), singerTbVO);
            singerTbVOList.add(singerTbVO);
        }

        //热门歌手

        List<ResultVO> list = new ArrayList<>();

        return ResultVOUtil.success(singerTbVOList);
    }
    //热门歌手
    @ApiOperation(value = "热门歌手")
    @GetMapping(value = "/singer/hotSinger")
    public ResultVO getHotSinger(){
        List<SingerTb> singerTbListHot = singerTbService.findAll();
        List<SingerTbVO> singerTbVOListHot = new ArrayList<>();
        for (SingerTb singerTb : singerTbListHot) {
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTb, singerTbVO);
            singerTbVOListHot.add(singerTbVO);
        }
        return  ResultVOUtil.success(singerTbVOListHot);
    }


    //所有入驻歌手
    @ApiOperation(value = "所有入驻歌手")
    @GetMapping(value = "/insinger")
    public ResultVO inSinger() {
        List<SingerTb> singerTbList = singerTbService.findAllInSinger();
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        for (int i = 0; i < singerTbList.size(); i++) {
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTbList.get(i), singerTbVO);
            singerTbVOList.add(singerTbVO);
        }
        return ResultVOUtil.success(singerTbVOList);
    }

    //分类歌手
    @ApiOperation(value = "分类歌手", notes = "根据歌手类型和首字母查询")
    @GetMapping(value = "/singer/cat")
    public ResultVO singerCat(@RequestParam(value = "singerType") Integer singerType,
                              @RequestParam(value = "initial", defaultValue = "hot") String initial) {
        List<SingerTbVO> singerTbVOList = new ArrayList<>();
        List<SingerTb> singerTbList = new ArrayList<>();
        if (initial.equals("hot")) {
            singerTbList = singerTbService.findByType(singerType);
        } else {
            singerTbList = singerTbService.findByTypeAndInitial(singerType, initial);
        }
        for (SingerTb singerTb : singerTbList) {
            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTb, singerTbVO);
            singerTbVOList.add(singerTbVO);
        }

        return ResultVOUtil.success(singerType, SingerTypeEnum.getSting(singerType), singerTbVOList);
    }


//    //所有的歌手信息
//    @GetMapping(value = "/allsinger")
//    public ResultVO allSinger(@RequestParam(value = "singerType",defaultValue = "999") String singerTypeS,
//                              @RequestParam(value = "initial",defaultValue = "666") String initial){
//        Integer singerType = Integer.valueOf(singerTypeS);
//        return ResultVOUtil.success();
//    }

    //歌手页
    @ApiOperation(value = "歌手页", notes = "根据内容查询artist、album、mv、intro")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "content", value = "不同内容", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "singerId", value = "歌手id", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "pageNumber", value = "页数", dataType = "String"),
//            @ApiImplicitParam(name = "pageSize", value = "每页最大内容", dataType = "String")
//    })
    @GetMapping(value = "/singerpage")
    public ResultVO singerPage(@RequestParam(value = "content") String content,
                               @RequestParam(value = "singerId") Integer singerId,
                               @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        //歌手大图
        SingerTb singerTb = singerTbService.findBySingerId(singerId);
        SingerPageVO singerPageVO = new SingerPageVO();
        BeanUtils.copyProperties(singerTb, singerPageVO);
        PageResultVO pageResultVO = null;


        //相似歌手
        List<SingerTb> singerTbList = new ArrayList<>();


        for (SingerTb singerTb1 : singerTbService.findByTypeAndInitial(singerTb.getSingerType(), singerTb.getInitial())) {
            if (singerTb1.getSingerId().equals(singerTb.getSingerId())) {
                continue;
            }
            singerTbList.add(singerTb1);
        }

        singerPageVO.setSimilarSinger(singerTbList.stream().limit(6).collect(Collectors.toList()));

        switch (content) {
            case "artist":
                PageInfo<SongTb> songTbPageInfo = songTbService.findBySingerId(singerId, 1, 50);
                singerPageVO.setData(songTbPageInfo.getList());
                break;
            case "album":
                PageInfo<AlbumTb> albumTbPageInfo = albumTbService.findBySingerIdSortIssueTime(singerId, pageNumber, pageSize);
                List<AlbumTbVO> albumTbVOList = new ArrayList<>();
                for (AlbumTb albumTb : albumTbPageInfo.getList()) {
                    AlbumTbVO albumTbVO = new AlbumTbVO();
                    BeanUtils.copyProperties(albumTb, albumTbVO);
                    albumTbVO.setIssueTime(DateUtil.dateToString(albumTb.getIssueTime()));
                    albumTbVOList.add(albumTbVO);
                }
                pageResultVO = new PageResultVO(albumTbPageInfo.getPages(), albumTbPageInfo.getTotal(), albumTbPageInfo.getPageNum(), albumTbPageInfo.getSize(), albumTbVOList);
                singerPageVO.setData(pageResultVO);
//                return ResultVOUtil.success(albumTbVOList);
                break;
            case "mv":
                PageInfo<MvTb> mvTbPageInfo = mvTbService.findBySingerId(singerId, pageNumber, pageSize);
                List<MvTbVO> mvTbVOList = new ArrayList<>();
                for (MvTb mvTb : mvTbPageInfo.getList()) {
                    MvTbVO mvTbVO = new MvTbVO();
                    BeanUtils.copyProperties(mvTb, mvTbVO);
                    mvTbVOList.add(mvTbVO);
                }
                pageResultVO = new PageResultVO(mvTbPageInfo.getPages(), mvTbPageInfo.getTotal(), mvTbPageInfo.getPageNum(), mvTbPageInfo.getSize(), mvTbVOList);
                singerPageVO.setData(pageResultVO);
//                return ResultVOUtil.success(mvTbVOList);
                break;
            case "intro":
//                return ResultVOUtil.success(singerTb.getSingerIntro());
                singerPageVO.setData(singerTb.getSingerIntro());
                break;
            default:
                throw new MusicException(ExceptionEnum.UNKNOWN_TYPE);
        }

        return ResultVOUtil.success(singerPageVO);
    }

    @ApiOperation(value = "获取所有歌手", notes = "根据人气排序")
    @GetMapping(value = "/singer/list")
    public ResultVO getSingerList(@RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize) {
        List<SingerUserVO> result = new ArrayList<>();

        PageInfo<SingerTb> singerTbPageInfo = singerTbService.findAllPage(pageNumber, pageSize);
        for (SingerTb singerTb : singerTbPageInfo.getList()) {
            SingerUserVO singerUserVO = new SingerUserVO();

            SingerTbVO singerTbVO = new SingerTbVO();
            BeanUtils.copyProperties(singerTb, singerTbVO);
            if (singerTb.getUserId() != null) {
                UserTbVO userTbVO = new UserTbVO();
                UserTb userTb = userTbService.findById(singerTb.getUserId());
                BeanUtils.copyProperties(userTb, userTbVO);
                singerUserVO.setUserTbVO(userTbVO);
            } else {
                singerUserVO.setUserTbVO(null);
            }

            singerUserVO.setSingerTbVO(singerTbVO);
            result.add(singerUserVO);
        }
        PageResultVO pageResultVO = new PageResultVO(singerTbPageInfo.getPages(), singerTbPageInfo.getTotal(), singerTbPageInfo.getPageNum(), singerTbPageInfo.getSize(), result);

        return ResultVOUtil.success(pageResultVO);
    }


    @ApiOperation(value = "根据Id删除一个歌手")
    @GetMapping("/delete/singer")
    public ResultVO deleteSinger(@RequestParam("singerId") Integer singerId) {
        singerTbService.deleteById(singerId);
        return ResultVOUtil.success();
    }

    @ApiOperation(value = "更新歌手信息")
    @PostMapping("/update/singer")
    public ResultVO updateSinger(@RequestParam(value = "singerName", required = false) String singerName,
                                 @RequestParam(value = "singerImg", required = false) MultipartFile singerImg,
                                 @RequestParam(value = "singerOneIntro", required = false) String singerOneIntro,
                                 @RequestParam(value = "singerIntro", required = false) String singerIntro,
                                 @RequestParam(value = "singerId") Integer singerId) throws IOException {
        SingerTb singerTb = singerTbService.findBySingerId(singerId);
        if (singerName != null) {
            singerTb.setSingerName(singerName);
        }
        if (singerImg != null) {
            FileInputStream fileInputStream = (FileInputStream) singerImg.getInputStream();
            String filePath = UploadUtil.commonUpload(fileInputStream, singerImg.getOriginalFilename());
            singerTb.setSingerImg(filePath);
        }
        if (singerOneIntro != null) {
            singerTb.setSingerOneIntro(singerOneIntro);
        }
        if (singerIntro != null) {
            singerTb.setSingerIntro(singerIntro);
        }
        SingerTb result = singerTbService.updateSinger(singerTb);
        return ResultVOUtil.success(result);
    }

    @ApiOperation(value = "创建歌手")
    @PostMapping("/create/singer")
    public ResultVO createSinger(@RequestParam(value = "singerName") String singerName,
                                 @RequestParam(value = "singerImg") MultipartFile singerImg,
                                 @RequestParam(value = "singerOneIntro") String singerOneIntro,
                                 @RequestParam(value = "singerIntro") String singerIntro) throws IOException {
        SingerTb singerTb = new SingerTb();
        singerTb.setSingerName(singerName);
        FileInputStream fileInputStream = (FileInputStream) singerImg.getInputStream();
        String filePath = UploadUtil.commonUpload(fileInputStream, singerImg.getOriginalFilename());
        singerTb.setSingerImg(filePath);
        singerTb.setSingerOneIntro(singerOneIntro);
        singerTb.setSingerIntro(singerIntro);
        singerTb.setInitial(String.valueOf(initialUtil.getStringPinyin(singerName).toUpperCase().charAt(0)));

        if(singerTbService.createSinger(singerTb)==1){
            return ResultVOUtil.success();
        }else {
            throw new MusicException(ExceptionEnum.INSERT_ERROR);
        }
    }
}
