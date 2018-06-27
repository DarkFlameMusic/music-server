package com.musicweb.music.controller;

import com.musicweb.music.VO.ResultVO;
import com.musicweb.music.VO.UserTbVO;
import com.musicweb.music.entity.MailSendTo;
import com.musicweb.music.entity.UserTb;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.enums.GenderEnum;
import com.musicweb.music.enums.UserJurisdictionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.MailServiceImpl;
import com.musicweb.music.service.impl.SongListTbServiceImpl;
import com.musicweb.music.service.impl.UserTbServiceImpl;
import com.musicweb.music.utils.*;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;



@Api(value = "登陆注册")
@RestController
@RequestMapping(value = "/api")
public class LoginAndRegisterController {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Autowired
    private MailServiceImpl mailService;

    //登陆
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                          HttpServletResponse response) throws Exception {
        //在数据库中对比
        UserTb userTb = userTbService.findByUsername(username);
        VerifyUserUtil.verifyUser(userTb, MD5Util.encode(password));
        //设置用户标识码
        String token = TokenUtil.createToken(userTb.getUserId(),username,10000*60*60*10);
        UserTbVO userTbVO = new UserTbVO();
        BeanUtils.copyProperties(userTb,userTbVO);
        userTbVO.setBirthDate(DateUtil.dateToString(userTb.getBirthDate()));
        CookieUtil.set(response,"Token",token,60000);
        return ResultVOUtil.success(0,"登陆成功",ResultVOUtil.success(0,token,null));
    }

    //登出
    @ApiOperation(value = "登出")
    @GetMapping(value = "/logout")
    public ResultVO logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = CookieUtil.get(request,"Token");
        if (cookie != null){
            CookieUtil.set(response,"Token",null,0);
        }
        //TODO 跳转页面
        return ResultVOUtil.success(0,"登出",null);
    }

    //TODO  注册功能
    @ApiOperation(value = "注册",notes = "传入用户名 密码 昵称")
    @PostMapping(value = "/signin")
    public ResultVO signIn(@RequestBody @Valid UserTb userTb,
                           BindingResult bindingResult){
//        UserTb userTb = new UserTb();
//        userTb.setUsername(username);
//        userTb.setPassword(password);
//        userTb.setUserNickname(userNickname);

        if(bindingResult.hasErrors()){
            return ResultVOUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }
        if (userTbService.findByUsername(userTb.getUsername()) != null){
            throw new MusicException(ExceptionEnum.USERNAME_REPEAT);
        }


        //用户输入属性
        userTb.setUsername(userTb.getUsername());
        userTb.setPassword(MD5Util.encode(userTb.getPassword()));
        userTb.setUserNickname(userTb.getUserNickname());
        //默认属性
        userTb.setMail(userTb.getUsername());
        userTb.setJurisdiction(UserJurisdictionEnum.WAIT.getCode());
        userTb.setGender(GenderEnum.UNKNOWN_GENDER.getCode());

        // 随机生成邮箱用户验证码，存入数据库中
        MailSendTo mailSendTo = new MailSendTo();
        //TODO message 为一个激活链接 设置过期时间
        String captcha = UUID.randomUUID().toString();
        String message = "http://localhost:8080/api/register?code="+captcha;
        //TODO 发送邮件失败然后xxxxxx
        mailService.sendMail(userTb.getUsername(),"test",message);

        userTb.setCaptcha(captcha);

        userTbService.insertForSignIn(userTb);
        return ResultVOUtil.success(userTb);
    }


    // （判断图片验证码是否正确，正确则发送） 通过邮箱发送用户验证码，验证之后修改权限
    @ApiOperation(value = "注册验证")
    @GetMapping(value = "/register")
    public ResultVO register(HttpServletRequest request){
        String captcha = request.getParameter("code");
        UserTb userTb = userTbService.findByCaptcha(captcha);

        userTb.setJurisdiction(UserJurisdictionEnum.USER.getCode());
        userTb.setCaptcha(null);
        userTb.setHeadImg("default");
        userTbService.compilePersonalData(userTb);

        return ResultVOUtil.success(0,"注册成功",null);
    }

    @ApiOperation(value = "简单注册",notes = "无需通过邮箱注册")
    @PostMapping(value = "/simplesignin")
    public ResultVO simpleSignIn(@Valid UserTb userTb,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultVOUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }
        //用户输入属性
        userTb.setUsername(userTb.getUsername());
        userTb.setPassword(MD5Util.encode(userTb.getPassword()));
        userTb.setUserNickname(userTb.getUserNickname());
        //默认属性
        userTb.setMail(userTb.getUsername());
        userTb.setJurisdiction(UserJurisdictionEnum.WAIT.getCode());
        userTb.setGender(GenderEnum.UNKNOWN_GENDER.getCode());
        userTbService.insertForSignIn(userTb);
        return ResultVOUtil.success(userTb);
    }

    //修改密码
    @ApiOperation(value = "修改密码",notes = "通过用户名和旧密码修改")
    @PostMapping(value = "/recompose")
    public ResultVO recompose(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("newpassword") String newPassword){
        UserTb userTb = userTbService.findByUsername(username);
        VerifyUserUtil.verifyUser(userTb,password);
        userTb.setPassword(MD5Util.encode(newPassword));
        userTbService.compilePersonalData(userTb);

        return ResultVOUtil.success(userTb);
    }

    //通过邮箱修改密码  忘记密码
    @ApiOperation(value = "通过邮箱验证修改密码")
    @PostMapping(value = "/recomposeformail")
    public ResultVO recomposeForMail(@RequestParam("username") String username){
        UserTb userTb = userTbService.findByUsername(username);
        VerifyUserUtil.verifyUser(userTb,userTb.getPassword());

        MailSendTo mailSendTo = new MailSendTo();
        String captcha = UUID.randomUUID().toString();
        String message = "http://localhost:8080/api/newpassword?code=" + captcha;

        mailService.sendMail(username,"修改密码",message);
        userTb.setCaptcha(captcha);
        userTbService.compilePersonalData(userTb);
        return ResultVOUtil.success(userTb);
    }


    //修改密码激活链接
    @ApiOperation(value = "修改密码激活链接")
    @PostMapping(value = "/newpassword")
    public ResultVO newPassword(HttpServletRequest request,@RequestParam(value = "newpassword") String newPassword){
        String captcha = request.getParameter("code");
        UserTb userTb = userTbService.findByCaptcha(captcha);

        VerifyUserUtil.verifyCaptcha(userTb);

        userTb.setPassword(MD5Util.encode(newPassword));
        userTb.setCaptcha(null);
        userTbService.compilePersonalData(userTb);

        return ResultVOUtil.success(userTb);
    }


}
