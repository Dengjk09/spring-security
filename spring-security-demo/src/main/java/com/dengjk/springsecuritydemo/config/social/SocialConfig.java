package com.dengjk.springsecuritydemo.config.social;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfiguration;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author Dengjk
 * @create 2018-08-11 11:08
 * @desc  配置第三方登入信息
 **/
@Configuration
@EnableSocial
public class SocialConfig  extends SocialConfigurerAdapter{

    @Autowired
    private DruidDataSource druidDataSource;

    /**
     * 重写父类的一个方法 主要用来配置第三方信息的存储
     *  JdbcUsersConnectionRepository  返回一个JdbcUsersConnectionRepository  在这个包同级有相关的建表语句  表明不能改变，可以改前缀
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        /**
         * 参数1  注入一个数据源
         * 参数2  根据类型构建一个connectionfactory  qq就注入qq的connectionfactory  微信就注入微信connectionfactory
         * 参数3  针对用户信息存入数据库来加密的
         */

        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(druidDataSource, connectionFactoryLocator, Encryptors.noOpText());
        return jdbcUsersConnectionRepository;
    }

    /**
     * 实例化SpringSocialConfigurer  放置容器中   加入到springsecurity的核心配置中
     * @return
     */
    @Bean
    public SpringSocialConfigurer socialConfigurer(){
        return new SpringSocialConfigurer();
    }
}
