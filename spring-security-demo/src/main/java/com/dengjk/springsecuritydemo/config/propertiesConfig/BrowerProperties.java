package com.dengjk.springsecuritydemo.config.propertiesConfig;

public class BrowerProperties {

    private String longinPage ="templates/mylogin.html";

    private LoginTypeEnum loginType =LoginTypeEnum.JSON;

    private  int remeberMeTokenTime =1800;

    public String getLonginPage() {
        return longinPage;
    }

    public void setLonginPage(String longinPage) {
        this.longinPage = longinPage;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public int getRemeberMeTokenTime() {
        return remeberMeTokenTime;
    }

    public void setRemeberMeTokenTime(int remeberMeTokenTime) {
        this.remeberMeTokenTime = remeberMeTokenTime;
    }
}
