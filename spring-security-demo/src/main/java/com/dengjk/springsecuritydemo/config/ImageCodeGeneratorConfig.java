package com.dengjk.springsecuritydemo.config;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.service.ImageCodeGenerator;
import com.dengjk.springsecuritydemo.service.ImageCodeGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageCodeGeneratorConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean /**b本注解是用来表示  spring容器中是否有这个bean ,如果没有 就实例化这bean*/
    public ImageCodeGenerator imageCodeGenerator(){
        ImageCodeGeneratorImpl imageCodeGenerator = new ImageCodeGeneratorImpl();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}
