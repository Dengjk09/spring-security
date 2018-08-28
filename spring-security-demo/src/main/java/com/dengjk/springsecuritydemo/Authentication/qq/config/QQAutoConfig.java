package com.dengjk.springsecuritydemo.Authentication.qq.config;

import com.dengjk.springsecuritydemo.Authentication.qq.connaction.QQConnectionFactory;
import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.config.propertiesConfig.SocialProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author Dengjk
 * @create 2018-08-11 11:39
 * @desc  通过这个类来给自定义的QQConnectionFactory构造
 **/
@Configuration
@ConditionalOnProperty(prefix = "dengjk.security.social",name = "appId")  /**只有当我们在配置文件配置了这个值 这个配置文件才生效*/
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        SocialProperties social = securityProperties.getSocial();
        return new QQConnectionFactory(social.getProviderId(),social.getAppId(),social.getAppSecret());
    }
}
