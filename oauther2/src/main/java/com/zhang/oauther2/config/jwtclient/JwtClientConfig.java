//package com.zhang.oauther2.config.jwtclient;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//
///**
// * @Copyright 深圳金雅福控股集团有限公司
// * @Author: zhangfanjun
// * @Date 2021/11/17
// * @Version: 1.0
// */
//@Configuration
//public class JwtClientConfig {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public JwtClientDetailsServiceBuilder getJwtClientDetailsServiceBuilder() {
//        JwtClientDetailsServiceBuilder jwtClientDetailsServiceBuilder = new JwtClientDetailsServiceBuilder();
//        jwtClientDetailsServiceBuilder
//                .withClient("app-one")
//                .resourceIds("app1")
//                .authorizedGrantTypes("password")
//                .scopes("read","write","all")
//                .authorities("admin")
//                .secret("{bcrypt}" + new BCryptPasswordEncoder().encode("123456"))
//                .accessTokenValiditySeconds(6000000).refreshTokenValiditySeconds(6000000)
//                ;
//
//        return jwtClientDetailsServiceBuilder;
//    }
//}
