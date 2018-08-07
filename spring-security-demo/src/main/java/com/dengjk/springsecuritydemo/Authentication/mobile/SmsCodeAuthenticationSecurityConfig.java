package com.dengjk.springsecuritydemo.Authentication.mobile;

import com.dengjk.springsecuritydemo.config.MyAuthenticationFailureHnadle;
import com.dengjk.springsecuritydemo.config.MyAuthenticationSucessHnadle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 把我自定短信过滤去串联起来  短信登录配置
 *
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private MyAuthenticationFailureHnadle myAuthenticationFailureHnadle;

    @Autowired
    private MyAuthenticationSucessHnadle myAuthenticationSucessHnadle;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义SmsCodeAuthenticationFilter过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHnadle);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSucessHnadle);

        //设置自定义SmsCodeAuthenticationProvider的认证器userDetailsService
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
