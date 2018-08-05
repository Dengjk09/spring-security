package com.dengjk.springsecuritydemo.service.validate.sms;

/**
 * 发送短信接口
 */
public interface SmsCodeSender {
    void sendSms(String mobile ,String code);
}
