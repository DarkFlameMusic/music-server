package com.musicweb.music.controller;


import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.UserTbVO;
import com.musicweb.music.entity.*;
import com.musicweb.music.entity.collecttable.CollectSongListTb;
import com.musicweb.music.entity.commenttable.AlbumCommentTb;
import com.musicweb.music.entity.commenttable.SongCommentTb;
import com.musicweb.music.entity.commenttable.SongListCommentTb;
import com.musicweb.music.enums.CommentTypeEnum;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.enums.UserJurisdictionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.*;
import com.musicweb.music.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//TODO 普通用户功能 返回结果待修改

@RestController
@RequestMapping(value = "/api")
public class UserFunctionController {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private SongListTbServiceImpl songListTbService;

    @Autowired
    private SongListSongTbServiceImpl songListSongTbService;

    @Autowired
    private CollectSongListTbServiceImpl collectSongListTbService;

    @Autowired
    private AttentionTbServiceImpl attentionTbService;

    @Autowired
    private SongCommentTbServiceImpl songCommentTbService;

    @Autowired
    private SongListCommentTbServiceImpl songListCommentTbService;

    @Autowired
    private AlbumCommentTbServiceImpl albumCommentTbService;

    @Autowired
    private CommentAdmireTbServiceImpl commentAdmireTbService;

    @Autowired
    private CommentTbServiceImpl commentTbService;

    @Autowired
    private UserListenTbServiceImpl userListenTbService;

    private final static Logger logger = LoggerFactory.getLogger(UserFunctionController.class);


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

    //编辑个人信息
    @ApiOperation(value = "编辑个人信息")
    @PostMapping(value = "/compiledata")//应该用Put，但是用Put出现Required String parameter 'userNickname' is not present
    public ResultVO compileData(@RequestParam(value = "userNickname",required = false) String userNickname,
                                @RequestParam(value = "personIntro",required = false) String personIntro,
                                @RequestParam(value = "gender",required = false) Integer gender,
                                @RequestParam(value = "birthDate",required = false) String birthDate,
                                @RequestParam(value = "area",required = false) String area,
                                @RequestParam(value = "file",required = false) MultipartFile multipartFile,
                                HttpServletRequest request) throws ParseException, IOException {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());

        UserTb userTb = userTbService.findById(userId);
        VerifyUserUtil.verifyToken(userTb);
        userTb.setUserNickname(userNickname);
        userTb.setPersonIntro(personIntro);
        userTb.setGender(gender);

        if(birthDate!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(birthDate);
            userTb.setBirthDate(date);
        }
        userTb.setArea(area);
        if(multipartFile!=null){
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            userTb.setHeadImg(UploadUtil.commonUpload(fileInputStream,multipartFile.getOriginalFilename()));
        }
        userTbService.compilePersonalData(userTb);
        UserTbVO userTbVO = new UserTbVO();
        BeanUtils.copyProperties(userTb, userTbVO);
        userTbVO.setBirthDate(DateUtil.dateToString(userTb.getBirthDate()));
        return ResultVOUtil.success(0, "修改成功", userTbVO);
    }

    //上传头像
    @ApiOperation(value = "上传头像")
    @PostMapping(value = "/upload/headimg")
    public ResultVO uploadHeadImg(HttpServletRequest request,
                                  @RequestParam("file") MultipartFile multipartFile) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        UserTb userTb = userTbService.findById(userId);
        try {
            String oldImg = userTb.getHeadImg();

            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            String filePath;
            if (oldImg.substring(oldImg.lastIndexOf("/")).equals(MD5Util.getMD5(userId + userTb.getUsername()) + ".jpg")) {
                filePath = UploadUtil.coverUpload(fileInputStream, MD5Util.getMD5(userId + userTb.getUsername()), ".jpg");
            } else {
                filePath = UploadUtil.commonUpload(fileInputStream, MD5Util.getMD5(userId + userTb.getUsername()), ".jpg");
            }
            userTb.setHeadImg(filePath);
            userTbService.compilePersonalData(userTb);
            return ResultVOUtil.success(filePath);
        } catch (IOException e) {
            return null;
        }
    }



    //编辑歌单
    @ApiOperation(value = "编辑歌单")
    @PostMapping(value = "/compilesonglist")
    public ResultVO compileSongList(HttpServletRequest request,
                                    @RequestParam(value = "songListId",required = false) Integer songListId,
                                    @RequestParam(value = "songListName",required = false) String songListName,
                                    @RequestParam(value = "songListIntro",required = false) String songListIntro,
                                    @RequestParam(value = "label",required = false) String label,
                                    @RequestParam(value = "file",required = false) MultipartFile multipartFile) throws IOException {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        SongListTb songListTb = new SongListTb();
        songListTb.setSongListId(songListId);
        songListTb.setSongListName(songListName);
        songListTb.setSongListIntro(songListIntro);
        songListTb.setLabel(label);
        songListTb.setSongListImg(UploadUtil.commonUpload(fileInputStream,multipartFile.getOriginalFilename()));
        songListTbService.updateOne(songListTb);
        return ResultVOUtil.success(0, "修改成功", songListTb);
    }

    //向歌单添加歌曲
    @ApiOperation(value = "向歌单添加歌曲")
    @PostMapping(value = "/addsong")
    public ResultVO addSong(HttpServletRequest request,
                            @RequestParam("songListId") Integer songListId,
                            @RequestParam("songId") Integer songId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        if (songListSongTbService.findBySongListIdAndSongId(songListId,songId) != null){
            throw new MusicException(ExceptionEnum.REPEAT_INSERT);
        }
        SongListSongTb songListSongTb = new SongListSongTb();
        songListSongTb.setSongListId(songListId);
        songListSongTb.setSongId(songId);
        songListSongTb.setCreateTime(new Date());
        songListSongTbService.insertOne(songListSongTb);
        return ResultVOUtil.success(0, "添加成功", songListSongTb);
    }

    //从歌单中删除歌曲
    @ApiOperation(value = "从歌单中删除歌曲")
    @PostMapping(value = "/deletesong")
    public ResultVO deleteSong(HttpServletRequest request,
                               @RequestParam("songListId") Integer songListId,
                               @RequestParam("songId") Integer songId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        List<SongListSongTb> songListSongTbList = songListSongTbService.findBySongListId(songListId);
        for (SongListSongTb songListSongTb : songListSongTbList) {
            if (songListSongTb.getSongId() == songId) {
                songListSongTbService.deleteBySongListSongId(songListSongTb.getSongListSongId());
            } else {
                throw new MusicException(ExceptionEnum.SONG_NULL);
            }
        }
        return ResultVOUtil.success(0, "删除成功", null);
    }

    //收藏歌单
    @ApiOperation(value = "收藏歌单")
    @PostMapping(value = "/collectsonglist")
    public ResultVO collectSongList(HttpServletRequest request,
                                    @RequestParam("songListId") Integer songListId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        if (collectSongListTbService.findBySongListIdAndUserId(songListId,userId) != null){
            throw new MusicException(ExceptionEnum.REPEAT_COLLECT);
        }
        CollectSongListTb collectSongListTb = new CollectSongListTb();
        collectSongListTb.setUserId(userId);
        collectSongListTb.setSongListId(songListId);
        collectSongListTb.setCreateTime(new Date());
        collectSongListTbService.insertOne(collectSongListTb);
        return ResultVOUtil.success(0, "收藏成功", collectSongListTb);
    }

    //取消收藏
    @PostMapping(value = "/deletecollect")
    public ResultVO deleteCollect(HttpServletRequest request,
                                  @RequestParam("songListId") Integer songListId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        List<CollectSongListTb> collectSongListTbList = collectSongListTbService.findByUserId(userId);
        int result = 0;
        for (CollectSongListTb collectSongListTb : collectSongListTbList) {
            logger.info(collectSongListTb.toString());
            if (collectSongListTb.getSongListId().equals(songListId)) {
                result = collectSongListTbService.deleteOne(collectSongListTb.getCollectSongListId());
            }
        }
        if (result != 1) {
            throw new MusicException(ExceptionEnum.SONG_LIST_NULL);
        }
        return ResultVOUtil.success(0, "取消成功", null);
    }

    //关注其他用户
    @ApiOperation(value = "关注其他用户")
    @PostMapping(value = "/attentionuser")
    public ResultVO attentionUser(HttpServletRequest request,
                                  @RequestParam("userAttentionId") Integer userAttentionId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        if (attentionTbService.findByUserIdAndUserAttentionId(userId,userAttentionId) != null){
            throw new MusicException(ExceptionEnum.REPEAT_ATTENTION);
        }
        AttentionTb attentionTb = new AttentionTb();
        attentionTb.setUserId(userId);
        attentionTb.setUserAttentionId(userAttentionId);
        attentionTb.setCreateTime(new Date());
        attentionTbService.insertOne(attentionTb);
        return ResultVOUtil.success(0, "关注成功", null);
    }

    //取消关注
    @ApiOperation(value = "取消关注")
    @PostMapping(value = "/deleteattention")
    public ResultVO deteleteAttention(HttpServletRequest request,
                                      @RequestParam("userAttentionId") Integer userAttentionId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        List<AttentionTb> attentionTbList = attentionTbService.findByUserId(userId);
        int result = 0;
        for (AttentionTb attentionTb : attentionTbList) {
            logger.info(attentionTb.toString());
            if (attentionTb.getUserAttentionId().equals(userAttentionId)) {
                result = attentionTbService.deleteByAttentionId(attentionTb.getAttentionId());
            }
        }
        if (result != 1) {
            throw new MusicException(ExceptionEnum.ATTENTION_USER_NULL);
        }
        return ResultVOUtil.success(0, "取消关注成功", null);
    }
    //TODO 待修改 发表评论1.歌曲2.歌单3.专辑4.动态
//    @PostMapping(value = "/addsongcomment")
//    public ResultVO addSongComment(@RequestParam("Token") String token,
//                                   @RequestParam("songId") Integer songId,
//                                   @RequestParam("comment") String comment){
//        Claims claims = TokenUtil.parseToken(token);
//        Integer userId = Integer.valueOf(claims.getId());
//        SongCommentTb songCommentTb = new SongCommentTb();
//        songCommentTb.setUserId(userId);
//        songCommentTb.setSongId(songId);
//        songCommentTb.setComment(comment);
//        songCommentTb.setCreateTime(new Date());
//        songCommentTbService.insertOne(songCommentTb);
//
//        return ResultVOUtil.success(0,"发表评论成功",null);
//    }
//
//    @PostMapping(value = "/addsonglistcomment")
//    public ResultVO addSongListComment(@RequestParam("Token") String token,
//                                       @RequestParam("songListId") Integer songListId,
//                                       @RequestParam("comment") String comment){
//        Claims claims = TokenUtil.parseToken(token);
//        Integer userId = Integer.valueOf(claims.getId());
//        SongListCommentTb songListCommentTb = new SongListCommentTb();
//        songListCommentTb.setUserId(userId);
//        songListCommentTb.setSongListId(songListId);
//        songListCommentTb.setComment(comment);
//        songListCommentTb.setCreateTime(new Date());
//        songListCommentTbService.insertOne(songListCommentTb);
//
//        return ResultVOUtil.success(0,"发表评论成功",null);
//    }
//
//    @PostMapping(value = "/addalbumcomment")
//    public ResultVO addAlbumComment(@RequestParam("Token") String token,
//                                    @RequestParam("albumId") Integer albumId,
//                                    @RequestParam("comment") String comment){
//        Claims claims = TokenUtil.parseToken(token);
//        Integer userId = Integer.valueOf(claims.getId());
//        AlbumCommentTb albumCommentTb = new AlbumCommentTb();
//        albumCommentTb.setUserId(userId);
//        albumCommentTb.setAlbumId(albumId);
//        albumCommentTb.setComment(comment);
//        albumCommentTb.setCreateTime(new Date());
//        albumCommentTbService.insertOne(albumCommentTb);
//
//        return ResultVOUtil.success(0,"发表评论成功",null);
//    }
//    //发表评论 comment_tb
//    @PostMapping(value = "/addsongcomments")
//    public ResultVO addSongComments(@RequestParam("songId") Integer songId,
//                                     @RequestParam("comment") String comment,
//                                    HttpServletRequest request){
//        return ResultVOUtil.success(0,
//                addComment(songId, Integer.valueOf(TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue()).getId()), CommentTypeEnum.SONG_COMMENT.getCode(), comment, new Date()),
//                null);
//    }
//    @PostMapping(value = "/addsonglistcomments")
//    public ResultVO addSongListComments( @RequestParam("songListId") Integer songListId,
//                                     @RequestParam("comment") String comment,
//                                         HttpServletRequest request){
//        return ResultVOUtil.success(0,
//                addComment(songListId, Integer.valueOf(TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue()).getId()), CommentTypeEnum.SONG_COMMENT.getCode(), comment, new Date()),
//                null);
//    }
//    @PostMapping(value = "/addalbumcomments")
//    public ResultVO addAlbumComments( @RequestParam("albumId") Integer albumId,
//                                     @RequestParam("comment") String comment,
//                                      HttpServletRequest request){
//        return ResultVOUtil.success(0,
//                addComment(albumId, Integer.valueOf(TokenUtil.parseToken(CookieUtil.get(request,"Token").getValue()).getId()), CommentTypeEnum.SONG_COMMENT.getCode(), comment, new Date()),
//                null);
//    }

    @ApiOperation(value = "发表评论")
    @PostMapping(value = "/addcomments")
    public ResultVO addComments(@RequestParam("objectId") Integer objectId,
                                @RequestParam("comment") String comment,
                                @RequestParam("objectType") Integer objectType,
                                HttpServletRequest request) {
        return ResultVOUtil.success(0,
                addComment(objectId, Integer.valueOf(TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue()).getId()), objectType, comment, new Date()),
                null);
    }

    //删除评论
    @ApiOperation(value = "删除评论")
    @PostMapping(value = "/deletecomment")
    public ResultVO deleteComment(@RequestParam("commentId") Integer commentId,
                                  HttpServletRequest request) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        return ResultVOUtil.success(0, deleteComment(commentId), null);
    }

    //点赞 取消
    @ApiOperation(value = "点赞歌曲评论")
    @GetMapping(value = "/admirecommentsong")
    public ResultVO admireSongComment(HttpServletRequest request,
                                      @RequestParam("songId") Integer songId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        return ResultVOUtil.success(0,
                addAdmireComment(userId, songId, CommentTypeEnum.SONG_COMMENT.getCode(), new Date()),
                null);
    }

    @ApiOperation(value = "点赞歌单评论")
    @GetMapping(value = "/admirecommentsonglist")
    public ResultVO admireSongListComment(HttpServletRequest request,
                                          @RequestParam("songListId") Integer songListId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        return ResultVOUtil.success(0,
                addAdmireComment(userId, songListId, CommentTypeEnum.SONG_LIST_COMMENT.getCode(), new Date()),
                null);
    }

    @ApiOperation(value = "点赞专辑评论")
    @GetMapping(value = "/admirecommentalbum")
    public ResultVO admireAlbumComment(HttpServletRequest request,
                                       @RequestParam("albumId") Integer albumId) {
        Claims claims = TokenUtil.parseToken(CookieUtil.get(request, "Token").getValue());
        Integer userId = Integer.valueOf(claims.getId());
        return ResultVOUtil.success(0,
                addAdmireComment(userId, albumId, CommentTypeEnum.ALBUM_COMMENT.getCode(), new Date()),
                null);
    }

    //播放歌曲
    @ApiOperation(value = "播放歌曲")
    @GetMapping(value = "/playsong")
    public ResultVO playSong(HttpServletRequest request,
                             @RequestParam("songId") Integer songId) {
        Cookie cookie = CookieUtil.get(request, "Token");
        Claims claims = TokenUtil.parseToken(cookie.getValue());
        Integer userId = Integer.valueOf(claims.getId());
        UserListenTb userListenTb = userListenTbService.findByUserIdAndSongId(userId, songId);
//        logger.info(userListenTb.toString());
        userListenTb.setPlayNumber(userListenTb.getPlayNumber() + 1);
        userListenTbService.updateOne(userListenTb);
        return ResultVOUtil.success();
    }
    //查看听歌记录：（1）播放数最多的前十（2）所有
    //发动态
    //删除动态


    private String addAdmireComment(Integer userId, Integer id, Integer commentType, Date date) {
        List<CommentAdmireTb> commentAdmireTbList = commentAdmireTbService.findBycCommentType(commentType);
        for (CommentAdmireTb commentAdmireTb : commentAdmireTbList) {
            if (commentAdmireTb.getUserId() == userId && commentAdmireTb.getCommentId() == id) {
                commentAdmireTbService.deleteById(commentAdmireTb.getCommentAdmireId());
                return "取消点赞";
            }
        }
        CommentAdmireTb commentAdmireTb = new CommentAdmireTb();
        commentAdmireTb.setUserId(userId);
        commentAdmireTb.setCommentId(id);
        commentAdmireTb.setCommentType(commentType);
        commentAdmireTb.setCreateTime(date);
        commentAdmireTbService.insertOne(commentAdmireTb);
        return "点赞成功";
    }

    private String addComment(Integer objectId, Integer userId, Integer objectType, String comment, Date date) {
        CommentTb commentTb = new CommentTb();
        commentTb.setObjectId(objectId);
        commentTb.setUserId(userId);
        commentTb.setObjectType(objectType);
        commentTb.setComment(comment);
        commentTb.setCreateTime(date);
        commentTbService.insertOne(commentTb);
        return "发表评论成功";
    }

    private String deleteComment(Integer commentId) {
        commentTbService.deleteOne(commentId);
        return "删除评论成功";
    }
}
