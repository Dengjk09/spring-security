package com.dengjk.springsecuritydemo.service.validate.image;

import com.dengjk.springsecuritydemo.entity.ImageCodeEntity;
import com.dengjk.springsecuritydemo.service.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCodeEntity> {


    /**
     * 写入验证码到响应中
     * @param request
     * @param validateCode
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @Override
    protected void send(ServletWebRequest request, ImageCodeEntity validateCode) throws ServletRequestBindingException, IOException {
        ImageIO.write( validateCode.getBufferedImage()  , "JPEG", request.getResponse().getOutputStream());
    }
}
