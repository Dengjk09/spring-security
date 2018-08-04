package com.dengjk.springsecuritydemo.controller;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.entity.ImageCodeEntity;
import com.dengjk.springsecuritydemo.service.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ImageCodeController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public static final String SESSION_KEY = "IMAGE-SESSION-KEY";

    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    /**
     * 定义一个获取图片接口
     * 把图片的信息存放在session中便于认证
     *
     * @param request
     * @param response
     */
    @GetMapping("/code/image")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**生成图片*/
        ImageCodeEntity imageCodeEntity = imageCodeGenerator.createImageCode(request);
        sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY, imageCodeEntity);
        ImageIO.write(imageCodeEntity.getBufferedImage(), "JPG", response.getOutputStream());
    }
}
