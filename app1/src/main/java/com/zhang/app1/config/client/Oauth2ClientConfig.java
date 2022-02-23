
//
//package com.zhang.app1.config.client;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//import java.util.Arrays;
//
//
//@Configuration
//@EnableOAuth2Client
//public class Oauth2ClientConfig {
//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext context, OAuth2ProtectedResourceDetails details) {
//        OAuth2RestTemplate template = new OAuth2RestTemplate(details, context);
//        AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
//        authCodeProvider.setStateMandatory(false);
//        AccessTokenProviderChain provider = new AccessTokenProviderChain(
//                Arrays.asList(authCodeProvider));
//        template.setAccessTokenProvider(provider);
//        return template;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}
