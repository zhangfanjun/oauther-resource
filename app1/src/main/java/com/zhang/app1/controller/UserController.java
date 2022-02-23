package com.zhang.app1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping("/login")
    public Object login(){
        log.info("login：{}","接口访问");
        return null;
    }

    @RequestMapping("/get")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(Authentication authentication){
        log.info("请求访问了");
//        authentication.getCredentials();
//        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
//        String token = details.getTokenValue();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }
}
