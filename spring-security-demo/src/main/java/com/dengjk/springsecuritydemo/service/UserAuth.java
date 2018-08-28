package com.dengjk.springsecuritydemo.service;

import com.dengjk.springsecuritydemo.dao.UserRepositry;
import com.dengjk.springsecuritydemo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 实现Spring-security用户认证  使用spring-security提供的一个接口  UserDetailsService
 */
@Service
public class UserAuth implements UserDetailsService ,SocialUserDetailsService{


    @Autowired
    private UserRepositry userRepositry;

    /**
     * 主要针对页面的表单登入
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /**通过用户名来找出用户密码*/
        UserEntity user = userRepositry.findByNameOrMobile(username,username);
        if(user==null){
            throw  new BadCredentialsException("用户不存在或者验证码不对。。。");
        }
        /**  user对象有 两个构造方法,一个是只有三个参数的  另一个是7个参数的
         * 一个参数是  认证的用户名字
         * 二个参数是  认证的密码
         * 三个参数是   授权的权限  是要接受一个集合GrantedAuthority  通过authortityUtils构建一个集合
         * */
        /**简单构造 只需认证用户和密码就ok了*/
        User admin = new User(user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        /**
         *
         *  3.代表这个用户是被否启用
         *  4. 用户没过去
         *  5. 密码也没过期
         *  6. 用户是否被锁定
         */
        User adminDetail = new User(username, user.getPassword(), true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return adminDetail;
    }


    /**
     *  社交登入  是以社交平台的id最为唯一标示来作为主键登入的
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        SocialUser adminDetail = new SocialUser(userId, userId, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return adminDetail;
    }
}
