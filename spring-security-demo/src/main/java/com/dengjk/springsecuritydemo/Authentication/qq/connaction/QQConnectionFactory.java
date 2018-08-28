package com.dengjk.springsecuritydemo.Authentication.qq.connaction;

import com.dengjk.springsecuritydemo.Authentication.qq.QQApi;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author Dengjk
 * @create 2018-08-11 10:58
 * @desc    用来实例化对象访问父类
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQApi> {
    /**
     * 编写构造函数来实例化
     *
     *  由配置类 QQAutoConfig来实例化
     */
    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
