package com.dengjk.springsecuritydemo.Authentication.qq;

import com.dengjk.springsecuritydemo.Authentication.qq.connaction.QQUserInfo;

/**
 * 统一的qq接口
 */
public interface QQApi {

   QQUserInfo getUserInfo();

}
