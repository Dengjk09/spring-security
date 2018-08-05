package com.dengjk.springsecuritydemo.service.validate;


import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/***
 * 校验码处理器   封装不同校验码逻辑
 */

public interface ValidateCodeProcessor {


    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /**
     * 创建校验码
     * @param request
     */
    void create(ServletWebRequest request) throws IOException, ServletRequestBindingException;
}
