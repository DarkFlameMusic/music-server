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
import com.musicweb.music.utils.UploadUtil;
import io.swagger.annotations.ApiOperation;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CarouselImgController {

    @Autowired
    private CarouselImgTbServiceImpl carouselImgTbService;

    //首页轮播图
    @ApiOperation(value = "获取轮播图接口")
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

    //上传轮播图
    @ApiOperation(value = "上传轮播图")
    @PostMapping(value = "/upload/carousel")
    public ResultVO uploadCarouselImg(HttpServletRequest request,
                                      @RequestParam(value = "file") MultipartFile multipartFile,
                                      @RequestParam(value = "linkUrl") String linkUrl,
                                      @RequestParam(value = "number",required = false) Integer number) {
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            String filePath = UploadUtil.commonUpload(fileInputStream,multipartFile.getOriginalFilename());
//            String filePath = UploadUtil.coverUpload(fileInputStream, CAROUSELIMGNAME + number, ".jpg");
            CarouselImgTb carouselImgTb = new CarouselImgTb();
            carouselImgTb.setCarouselImg(filePath);
            carouselImgTb.setCarouselUrl(linkUrl);
            carouselImgTb.setCreateTime(new Date());
            CarouselImgTbVO carouselImgTbVO = new CarouselImgTbVO();
            carouselImgTbService.insertOne(carouselImgTb);
            carouselImgTbVO.setCarouselUrl(linkUrl);
            carouselImgTbVO.setCarouselImg(filePath);

            return ResultVOUtil.success(carouselImgTbVO);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //更新轮播图
    @ApiOperation(value = "更新轮播图")
    @PostMapping("/update/carousel")
    public ResultVO adminUpdateCarousel(@RequestParam("id") Integer id,
                                        @RequestParam(value = "imgFile",required = false) MultipartFile imgFile,
                                        @RequestParam(value = "linkUrl", required = false) String linkUrl) throws IOException {
        CarouselImgTb carouselImgTb = carouselImgTbService.getCarouselImgTbById(id);
        if(imgFile!=null){
            FileInputStream fileInputStream = (FileInputStream) imgFile.getInputStream();
            String filePath = UploadUtil.commonUpload(fileInputStream,imgFile.getOriginalFilename());
            carouselImgTb.setCarouselImg(filePath);
        }
        if(linkUrl!=null){
            carouselImgTb.setCarouselUrl(linkUrl);
        }
        carouselImgTbService.updateOne(carouselImgTb);
        carouselImgTbService.getCarouselImgTbById(carouselImgTb.getCarouselImgId());
        return ResultVOUtil.success(carouselImgTb);
    }

//    //获取所有轮播图
//    @ApiOperation(value = "获取所有轮播图")
//    @GetMapping("/allCarousel")
//    public ResultVO getCarousel(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
//                                @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
//        PageInfo<CarouselImgTb> carouselImgTbPageInfo = carouselImgTbService.findAll(pageNumber, pageSize);
//
//        PageResultVO pageResultVO = new PageResultVO(carouselImgTbPageInfo.getPages(), carouselImgTbPageInfo.getTotal(), carouselImgTbPageInfo.getPageNum(), carouselImgTbPageInfo.getSize(), carouselImgTbPageInfo.getList());
//        return ResultVOUtil.success(pageResultVO);
//    }

}
