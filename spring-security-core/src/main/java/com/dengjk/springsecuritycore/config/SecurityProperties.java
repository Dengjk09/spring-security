package com.dengjk.springsecuritycore.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dengjk.security")
public class SecurityProperties {

    private BrowerProperties browser = new BrowerProperties();

}
