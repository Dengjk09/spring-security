package com.dengjk.springsecuritydemo.controller;

import com.dengjk.springsecuritydemo.commone.utils.ResultData;
import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


/**
 * 配置一个controller来指定spring-security登入跳转   针对  html和app的跳转  如果是html请求  ,就应该跳转到登入页面 ,如果是app请求就应该相应json数据
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * spring-securit提供的   请求缓存  ,会把当前的请求缓存到session里面去   当登入跳转到这个requireAuth  映射上  ,会把缓存中的请求拿出来
     */
    private RequestCache requestCache =new HttpSessionRequestCache();

    @Autowired
    private SecurityProperties securityProperties;

    /***
     * spring-security引发跳转的对象
     */
    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();


    @GetMapping("/requireAuth")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultData requireAuth(HttpServletRequest request , HttpServletResponse response) throws IOException {

        /**获取缓存的请求*/
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null){
            String redirectUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                logger.info("登入成功跳转的地址:{}",redirectUrl);
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLonginPage());
            }
        }
        return ResultData.AUTHFAIL("认证失败");
    }

    /**
     * 获取spring-security登入成功时候springSecurityContext中的用户信息 SecurityContextHolder.getContext().getAuthentication();
     *  获取直接在方法参数上注入 Authentication对象 ,springmvc会去实例化这个对象
     * @return
     */
    @GetMapping("/userInfo")
    public  Object getUserInfo(Authentication authentication){
        return authentication;
    }
}
