package com.dengjk.springsecuritydemo.controller;

import com.dengjk.springsecuritydemo.service.validate.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 重构之后的代码  体现实现分层思想  便于扩展
 */
@RestController
public class ValidateCodeController {

    /**
     * 注入接口所有的实现  根据类型来判断走哪个实现
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;


    /**
     * 根据类型来判断走哪个实现
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping("/code/{type}")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response,@PathVariable("type") String type) throws Exception {
        validateCodeProcessors.get(type+"CodeProcessor").create(new ServletWebRequest(request,response));
    }
}
