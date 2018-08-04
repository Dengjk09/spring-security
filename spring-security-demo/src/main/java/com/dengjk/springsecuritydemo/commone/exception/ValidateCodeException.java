package com.dengjk.springsecuritydemo.commone.exception;


import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String s) {
        super(s);
    }
}
