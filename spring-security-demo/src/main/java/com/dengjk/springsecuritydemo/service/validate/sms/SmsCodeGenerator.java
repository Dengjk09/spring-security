package com.dengjk.springsecuritydemo.service.validate.sms;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.entity.ValidateCodeEntity;
import com.dengjk.springsecuritydemo.service.validate.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;

/***
 * 图片生成器
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCodeEntity generate(ServletWebRequest request) {
        String num = RandomStringUtils.randomNumeric(6);
        ValidateCodeEntity validateCodeEntity = new ValidateCodeEntity(num,60);
        return validateCodeEntity;
    }
}
