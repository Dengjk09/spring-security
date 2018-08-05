package com.dengjk.springsecuritydemo.service.validate.sms;


public class DefaultSendSmsImpl implements SmsCodeSender {
    @Override
    public void sendSms(String mobile, String code) {
        System.out.println("向用户手机号为:" + mobile + "   发送短信验证码: " + code);
    }
}
