package com.zhang.oauther2.controller;

import com.alibaba.fastjson.JSON;
import com.zhang.oauther2.config.myclientdetail.MyClientDetailsInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@RequestMapping("/myClientDetail")
@RestController
public class MyClientDetailController {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping("/getMyClientDetails")
    public Object getMyClientDetails(){
        return MyClientDetailsInfo.temp;
    }

    @RequestMapping("/add")
    public Object add(String clientId,String clientSecret){
        BaseClientDetails one = new BaseClientDetails();
        one.setClientSecret(passwordEncoder.encode(clientSecret));
        one.setClientId(clientId);
        return MyClientDetailsInfo.temp.put(clientId, JSON.toJSONString(one));
    }

    @RequestMapping("/remove")
    public Object remove(String clientId){
        return MyClientDetailsInfo.temp.remove(clientId);
    }
}
