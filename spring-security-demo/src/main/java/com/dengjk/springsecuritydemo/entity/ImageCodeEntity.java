package com.dengjk.springsecuritydemo.entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图形验证码实体
 */
public class ImageCodeEntity  extends ValidateCodeEntity implements Serializable{

    private BufferedImage bufferedImage;

    public ImageCodeEntity(BufferedImage bufferedImage, String code, int time) {
        super(code,time);
        this.bufferedImage = bufferedImage;
    }
    /**
     * 验证是否过期
     * @return
     */
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(super.getLocalDateTime());
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
