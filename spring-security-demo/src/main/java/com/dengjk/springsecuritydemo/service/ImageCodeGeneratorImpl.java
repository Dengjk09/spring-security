package com.dengjk.springsecuritydemo.service;

import com.dengjk.springsecuritydemo.config.propertiesConfig.SecurityProperties;
import com.dengjk.springsecuritydemo.entity.ImageCodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 在这里可能每个要求图片的格式大小都不一样 ,不直接把实现放入bean容器中   ,默认的话 通过一个bean来引入
 */
public class ImageCodeGeneratorImpl implements ImageCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 生成图片对象
     *
     * @param request
     * @return
     */
    @Override
    public ImageCodeEntity createImageCode(HttpServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getImageCode().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < securityProperties.getImageCode().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCodeEntity(image, sRand, 60);
    }

    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
