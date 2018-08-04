package com.dengjk.springsecuritydemo.entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码实体
 */
public class ImageCodeEntity {

    private BufferedImage bufferedImage;

    private String code;

    private LocalDateTime  localDateTime;


    public ImageCodeEntity(BufferedImage bufferedImage, String code, int time) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.localDateTime = LocalDateTime.now().plusSeconds(time);
    }

    /**
     * 验证是否过期
     * @return
     */
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(localDateTime);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
