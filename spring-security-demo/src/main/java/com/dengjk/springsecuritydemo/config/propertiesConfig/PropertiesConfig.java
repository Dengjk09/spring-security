package com.dengjk.springsecuritydemo.config.propertiesConfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class PropertiesConfig {
}
