package com.dengjk.springsecuritydemo.dao;

import com.dengjk.springsecuritydemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepositry extends JpaRepository<UserEntity ,Integer>,JpaSpecificationExecutor<UserEntity> {

    UserEntity findByNameOrMobile(String name ,String mobile);
}
