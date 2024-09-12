package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenTestController {
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("/getToken")
    public String testToken(String userId){
        return tokenUtil.getToken(userId);
    }
}
