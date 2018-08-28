package com.dengjk.springsecuritydemo.config.propertiesConfig;

/**
 * @author Dengjk
 * @create 2018-08-11 11:33
 * @desc
 **/
public class SocialProperties {

    private String appId;

    private  String appSecret;

    private String providerId ="qq";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
