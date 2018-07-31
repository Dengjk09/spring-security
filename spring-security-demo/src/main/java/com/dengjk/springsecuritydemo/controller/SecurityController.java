package com.dengjk.springsecuritydemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@Api(value = "security-controller",description = "安全入口")
public class SecurityController {


    @GetMapping(value = "/firstIndex",produces = "application/json")
    @ApiOperation("入口")
    public String firstIndex(){
        return  "this my first spring security";
    }
}
