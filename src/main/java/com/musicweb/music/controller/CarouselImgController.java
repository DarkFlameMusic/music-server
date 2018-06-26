package com.musicweb.music.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.musicweb.music.VO.CarouselImgTbVO;
import com.musicweb.music.VO.PageResultVO;
import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.entity.CarouselImgTb;
import com.musicweb.music.service.impl.CarouselImgTbServiceImpl;
import com.musicweb.music.utils.DateUtil;
import com.musicweb.music.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CarouselImgController {

    @Autowired
    private CarouselImgTbServiceImpl carouselImgTbService;

    //首页轮播图
    @ApiOperation(value = "获取首页轮播图")
    @GetMapping("/banner")
    public ResultVO banner(@RequestParam("pageNumber") Integer pageNumber,
                           @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){

        PageInfo<CarouselImgTb> carouselImgTbPageInfo = carouselImgTbService.findAll(pageNumber,pageSize);
        List<CarouselImgTb> carouselImgTbList = carouselImgTbPageInfo.getList().stream().limit(6).collect(Collectors.toList());

//        carouselImgTbList.stream().limit(6).forEach(a->{CarouselImgTbVO b = new CarouselImgTbVO();BeanUtils.copyProperties(a,b);carouselImgTbVOList.add(b);});

        List<CarouselImgTbVO> carouselImgTbVOList = new ArrayList<>();
        for(CarouselImgTb carouselImgTb: carouselImgTbList){
            CarouselImgTbVO carouselImgTbVO = new CarouselImgTbVO();
            BeanUtils.copyProperties(carouselImgTb,carouselImgTbVO);
            carouselImgTbVO.setCreateTime(DateUtil.dateToString(carouselImgTb.getCreateTime()));
            carouselImgTbVOList.add(carouselImgTbVO);
        }
        PageResultVO pageResultVO =  new PageResultVO(carouselImgTbPageInfo.getPages(),carouselImgTbPageInfo.getTotal(),carouselImgTbPageInfo.getPageNum(),carouselImgTbPageInfo.getSize(),carouselImgTbVOList);
        return ResultVOUtil.success(pageResultVO);
    }

}
