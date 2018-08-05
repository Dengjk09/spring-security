package com.dengjk.springsecuritydemo.service.validate;

import com.dengjk.springsecuritydemo.entity.ValidateCodeEntity;
import org.springframework.web.context.request.ServletWebRequest;

/***
 * 生成器接口
 */
public interface ValidateCodeGenerator {

    ValidateCodeEntity generate(ServletWebRequest request);

}
