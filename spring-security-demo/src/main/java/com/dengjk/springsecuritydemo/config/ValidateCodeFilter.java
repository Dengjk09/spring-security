package com.dengjk.springsecuritydemo.config;

import com.dengjk.springsecuritydemo.commone.exception.ValidateCodeException;
import com.dengjk.springsecuritydemo.controller.ImageCodeController;
import com.dengjk.springsecuritydemo.entity.ImageCodeEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现图片验证码的认证     继承一个spring-web的过滤器来判断
 *
 * 要在spring-security的配置文件中配置  这个过滤器的一个执行顺序
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private  MyAuthenticationFailureHnadle myAuthenticationFailureHnadle;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**判断是否是表单请求的登入  并且还是post请求*/
        if (StringUtils.equalsIgnoreCase("/authentication/form", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            /**开始验证验证码*/
            try {
                validateImageCode(request);
            }catch (ValidateCodeException e) {
                myAuthenticationFailureHnadle.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validateImageCode(HttpServletRequest request) throws ServletRequestBindingException, ValidateCodeException {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);

        ImageCodeEntity codeInSession = (ImageCodeEntity) sessionStrategy.getAttribute(requestAttributes,
                ImageCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(requestAttributes, ImageCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(requestAttributes, ImageCodeController.SESSION_KEY);
    }
}
