package com.dengjk.springsecuritydemo.entity;

import java.time.LocalDateTime;

public class ValidateCodeEntity {

    private String code;

    private LocalDateTime localDateTime;

    public ValidateCodeEntity(String code, int  time) {
        this.code = code;
        this.localDateTime = LocalDateTime.now().plusSeconds(time);
    }

    public boolean isExpried(){
        return LocalDateTime.now().isAfter(localDateTime);
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
