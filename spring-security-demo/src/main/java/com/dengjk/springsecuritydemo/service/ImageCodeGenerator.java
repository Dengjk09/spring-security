package com.dengjk.springsecuritydemo.service;

import com.dengjk.springsecuritydemo.entity.ImageCodeEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码图片生成接口
 */
public interface ImageCodeGenerator {

    ImageCodeEntity createImageCode(HttpServletRequest request);
}
