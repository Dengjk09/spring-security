package com.dengjk.springsecuritydemo.Authentication.qq.connaction;

import com.dengjk.springsecuritydemo.Authentication.qq.QQApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author Dengjk
 * @create 2018-08-11 10:41
 * @desc  编写一个social 的apiadapter
 **/
public class QQAdapter implements ApiAdapter<QQApi>{

    /**
     * 用来测试qq服务是否正常
     * @param api
     * @return
     */
    @Override
    public boolean test(QQApi api) {
        return true;
    }


    /**
     * 把在第三方获取到的数据放到connectionValues中去
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQApi api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQApi api) {
        return null;
    }


    /**
     * 更新状态  主要针对个人主页  微博 facebook等
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQApi api, String message) {

    }
}
