package com.dengjk.springsecuritydemo.service.validate.impl;

import com.dengjk.springsecuritydemo.entity.ValidateCodeEntity;
import com.dengjk.springsecuritydemo.service.validate.ValidateCodeGenerator;
import com.dengjk.springsecuritydemo.service.validate.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCodeEntity> implements ValidateCodeProcessor{

    private SessionStrategy sessionStrategy =new HttpSessionSessionStrategy();

    /**
     * 注入一个map或者一个list  这种会把接口的所有实现多put到map中 ,key是实现类的名称
     */
    @Autowired
    private Map<String ,ValidateCodeGenerator> validateCodeGenerators;

    /***
     * 创建的方法
     *  分为三个步骤
     *  1 .生成
     *  2. 保存到session中
     *  3. 发送  (由于短信和图片两种的发送方式不一样,可以定义为抽象方法,让其子类去重写方法)
     *
     * @param request
     */
    @Override
    public void create(ServletWebRequest request) throws IOException, ServletRequestBindingException {
        /**生成处理*/
        C validateCode = generate(request);
        /**保存session*/
        save(request ,validateCode);
        /**开始保存*/
        send(request,validateCode);
    }
    private C generate(ServletWebRequest request) {
        /**可以根据请求的类型生成不同类型*/
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        /**真正根据类型生成*/
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码到session中
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        System.out.println(SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase());
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),
                validateCode);
    }

    /***
     *  因为图片和短信发送的方式不一样 定义一个抽象发送的方法,让其子类去重写这个方法
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws ServletRequestBindingException, IOException;

    /**
     * 判断url  生成那个验证
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
