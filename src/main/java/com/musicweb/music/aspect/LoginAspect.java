package com.musicweb.music.aspect;


import com.musicweb.music.entity.UserTb;
import com.musicweb.music.enums.ExceptionEnum;
import com.musicweb.music.enums.UserJurisdictionEnum;
import com.musicweb.music.exception.MusicException;
import com.musicweb.music.service.impl.UserTbServiceImpl;
import com.musicweb.music.utils.CookieUtil;
import com.musicweb.music.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {

    @Autowired
    private UserTbServiceImpl userTbService;

    @Pointcut("execution(public * com.musicweb.music.controller.UserFunctionController.*(..))")
    public void verifyToken(){}


    @Before("verifyToken()")
    public void doVerifyToken(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

       checkCookieAndToken(request);
    }
    @Before("execution(public * com.musicweb.music.controller.AdminFunctionController.*(..))")
    public void adminCheck(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Claims claims = checkCookieAndToken(request);
        UserTb userTb = userTbService.findById(Integer.valueOf(claims.getId()));
        Integer jurisdiction = userTb.getJurisdiction();
        if (!(jurisdiction.equals( UserJurisdictionEnum.ADMIN.getCode()) ||jurisdiction.equals(UserJurisdictionEnum.SINGER.getCode())) ){
            throw new MusicException(ExceptionEnum.JURISDICTION_ERROR);
        }
    }

    private Claims checkCookieAndToken(HttpServletRequest request){
        //查询cookie
        Cookie cookie = CookieUtil.get(request,"Token");
        if (cookie == null){
            throw new MusicException(ExceptionEnum.COOKIE_NULL);
        }
        Claims claims = null;
        try {
            claims = TokenUtil.parseToken(cookie.getValue());
        }catch (Exception e){
            throw new MusicException(ExceptionEnum.TOKEN_EXPIRE);
        }

        if (!Integer.valueOf(claims.getId()).equals(userTbService.findByUsername(claims.getSubject()).getUserId())){
            throw new MusicException(ExceptionEnum.TOKEN_ERROR);
        }
        return claims;
    }

}
