package com.musicweb.music.controller;


import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.service.impl.CommentTbServiceImpl;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api")
public class CommentController extends BasePageController{

    @Autowired
    private CommentTbServiceImpl commentTbService;


    @ApiOperation(value = "获取评论",notes = "输入页数、id、评论类型（0：歌曲，1：歌单，2：专辑）")
    @PostMapping(value = "/commentpages")
    public ResultVO comment(@RequestParam(value = "pageNumber") Integer pageNumber,
                                    @RequestParam("objectId") Integer objectId,
                                    @RequestParam("objectType") Integer objectType){
        return ResultVOUtil.success(getCommentPage(pageNumber,objectId,objectType));
    }
}
