package com.dengjk.springsecuritydemo.Authentication.qq.QQ;


import com.dengjk.springsecuritydemo.Authentication.qq.QQApi;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQApi> {

    private String appId;

    private static  final  String URL_AUTHENTION="https://graph.qq.com/oauth2.0/authorize";

    private static  final  String URL_ACCESS_TOKEN ="https://graph.qq.com/oauth2.0/token";

    /**
     * 构造方法
     */
    public QQServiceProvider(String appId,String appSecret) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHENTION,URL_ACCESS_TOKEN));
        this.appId =appId;
    }

    @Override
    public QQApi getApi(String accessToken) {
        return new QQApiImpl(accessToken,appId);
    }
}
