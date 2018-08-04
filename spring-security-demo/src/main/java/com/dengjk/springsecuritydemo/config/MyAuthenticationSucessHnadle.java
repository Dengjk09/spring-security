package com.dengjk.springsecuritydemo.config;

import com.alibaba.fastjson.JSON;
import com.dengjk.springsecuritydemo.commone.utils.ResultData;
import com.dengjk.springsecuritydemo.config.propertiesConfig.LoginTypeEnum;
import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.PathMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 对登入成功的用户进行处理访问自定义格式相应,而不是跳转到登入之前的地址  要在WebSecurityConfigurerAdapter的configure中添加这个对象
 *
 * 1 .实现接口 AuthenticationSuccessHandler
 * 3 .继承  spring-security实现好的一个类  SavedRequestAwareAuthenticationSuccessHandler
 */
@Configuration
public class MyAuthenticationSucessHnadle extends SavedRequestAwareAuthenticationSuccessHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /**
         * 实现配置化的  跳转 还是 响应json
         */
        if(LoginTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())){
            logger.info("登入成功开始响应自定义格式。。。");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(JSON.toJSONString(ResultData.SUCCESS(authentication)));
        }else {
            /**访问父类,就是实现跳转*/
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
