package com.dengjk.springsecuritydemo.Authentication.qq.connaction;

import com.dengjk.springsecuritydemo.Authentication.qq.QQApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;


/**
 * 实现我们QQapi重写获取用户的方法   继承自AbstractOAuth2ApiBinding 访问父类,会通过token去请求
 */
public class QQApiImpl extends AbstractOAuth2ApiBinding implements QQApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String URL_GET_OPPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";

    /**
     * 通过查看qq互联文档中   请求 需要三个参数 accessToken(在父类中已经有了 AbstractOAuth2ApiBinding) appId openId
     */
    private String appId;

    private String openId;

    public QQApiImpl(String accessToken, String appId) {
        /**指定为TokenStrategy.ACCESS_TOKEN_PARAMETER  就会把Token变为url上面的参数去请求*/
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        /**发起http请求  请求openId*/
        String apenIdUrl = String.format(URL_GET_OPPENID, accessToken);
        String responseOpenId = getRestTemplate().getForObject(apenIdUrl, String.class);
        logger.info("请求获取到的openId:  " + responseOpenId);
        this.openId = StringUtils.substringBetween(responseOpenId, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        /**获取用户信息*/
        String repStr = String.format(URL_GET_USERINFO, appId, openId);
        QQUserInfo qqUserInfo = getRestTemplate().getForObject(repStr, QQUserInfo.class);
        qqUserInfo.setOpenId(openId);
        return qqUserInfo;
    }
}
