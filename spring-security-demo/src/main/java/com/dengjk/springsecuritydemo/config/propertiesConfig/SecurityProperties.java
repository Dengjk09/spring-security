package com.dengjk.springsecuritydemo.config.propertiesConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dengjk.security")
public class SecurityProperties {

    private BrowerProperties browser = new BrowerProperties();
    private  ImageCodeProperties imageCode =new ImageCodeProperties();
    private SocialProperties social =new SocialProperties();


    public BrowerProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowerProperties browser) {
        this.browser = browser;
    }

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
