///*
// */
//
//package com.zhang.app1.config.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
//import org.springframework.web.client.RestTemplate;
//
//
//@Configuration
//@EnableResourceServer
//@Order(3)
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
////    @Autowired
//    protected RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    CustomizeIgnoreResourcesConfig ignoreResourcesConfig;
//
//    @Bean
//    public CustomizeAuthExceptionEntryPoint customizeAuthExceptionEntryPoint() {
//        return new CustomizeAuthExceptionEntryPoint();
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//
//        .csrf().disable()
//        .exceptionHandling()
//        .authenticationEntryPoint(customizeAuthExceptionEntryPoint()) // token异常自定义处理
//        .and()
//        .authorizeRequests()
//                .antMatchers("/app1/user/login").permitAll()
//        // 授权、测试、静态资源路劲开放，需要写在授权路劲前面才能过滤
//        .antMatchers(ignoreResourcesConfig.getResources()).permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .requestMatchers().antMatchers("/app1/**")
//        .and()
//        .httpBasic();
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        // 远程根据token获取用户详细信息，需要配置token-info-uri
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        UserAuthenticationConverter userTokenConverter = new CommonUserAuthenticationConverter();
//        accessTokenConverter.setUserTokenConverter(userTokenConverter);
//        remoteTokenServices.setRestTemplate(restTemplate);
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
//
//        resources
//                .resourceId("app1") // 设置资源id
//                .stateless(true)//无状态（不使用springSecurity的session管理登录态，默认为true）
//                .accessDeniedHandler(customizeAuthExceptionEntryPoint())
//                .authenticationEntryPoint(customizeAuthExceptionEntryPoint())
//                .tokenServices(remoteTokenServices);
//    }
//}
