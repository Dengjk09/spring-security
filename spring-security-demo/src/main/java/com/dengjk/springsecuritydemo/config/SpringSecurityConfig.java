package com.dengjk.springsecuritydemo.config;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/***
 * 针对浏览器发起请求的认证
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSucessHnadle myAuthenticationSucessHnadle;

    @Autowired
    private MyAuthenticationFailureHnadle myAuthenticationFailureHnadle;

    @Autowired
    private  ValidateCodeFilter validateCodeFilter;
    /**
     * 配置表单提交
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 为表单认证方式,当访问需要认证的接口时候 ,会先跳转到页面认证,认证完了之后就会跳转到想要访问的页面
         *
         */
        //http.httpBasic()
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) /**配置图片认证在  用户认证之前*/
                .formLogin()
                .loginPage("/api/auth/requireAuth")
                //.loginPage("/templates/mylogin.html")  /**单独配置这个页面会报一个错误   检测到该服务器正在将指向此网址的请求无限循环重定向。首先这个页面也是需要授权的,就会进入一个无线循环中·*/
                .loginProcessingUrl("/authentication/form") /**登入的处理中心  告知spring-security这个是登入的页面,要求验证用户和密码*/
                .successHandler(myAuthenticationSucessHnadle)
                .failureHandler(myAuthenticationFailureHnadle)
                .and()
                .authorizeRequests()  //请求认证
                .antMatchers("/templates/mylogin.html", "/api/auth/requireAuth", securityProperties.getBrowser().getLonginPage(), "/code/image").permitAll() /**解决无线循环   应该匹配到这个页面就放开   进入登入的页面*/
                .anyRequest()   //所有的请求
                .authenticated()  //都需要身份认证
                .and().csrf().disable();  //关闭csrf跨域防护

    }
}
