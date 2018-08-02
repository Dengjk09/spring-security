package com.dengjk.springsecuritydemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
@Api(value = "security-controller",description = "安全入口")
public class SecurityController {


    @GetMapping(value = "/firstIndex",produces = {"application/json","*/*"})
    @ApiOperation("入口")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String firstIndex(){
        return  "this my first spring security";
    }
}
