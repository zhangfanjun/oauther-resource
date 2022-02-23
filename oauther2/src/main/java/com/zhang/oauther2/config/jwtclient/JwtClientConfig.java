//package com.zhang.oauther2.config.jwtclient;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//

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
