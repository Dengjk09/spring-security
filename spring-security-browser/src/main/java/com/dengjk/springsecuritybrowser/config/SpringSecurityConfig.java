package com.dengjk.springsecuritybrowser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/***
 * 针对浏览器发起请求的认证
 */
//@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置表单提交
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 针对表单请求
         */
        http.formLogin()
                .and()
                .authorizeRequests()  //请求认证
                .anyRequest()   //所有的请求
                .authenticated();  //都需要身份认证
    }
}
