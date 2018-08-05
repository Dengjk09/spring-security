package com.dengjk.springsecuritydemo.config;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.service.validate.ValidateCodeGenerator;
import com.dengjk.springsecuritydemo.service.validate.image.ImageCodeGenerator;
import com.dengjk.springsecuritydemo.service.validate.sms.DefaultSendSmsImpl;
import com.dengjk.springsecuritydemo.service.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean
    public SmsCodeSender smsCodeSender(){
        DefaultSendSmsImpl defaultSendSms = new DefaultSendSmsImpl();
        return  defaultSendSms;
    }
}
