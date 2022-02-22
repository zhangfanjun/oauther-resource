package com.zhang.app2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourcAdapt extends ResourceServerConfigurerAdapter {
//    @Value("${security.oauth2.client.client-id}")
//    private String clientId;
//
//    @Value("${security.oauth2.client.client-secret}")
//    private String secret;
//
//    @Value("${security.oauth2.authorization.check-token-access}")
//    private String checkTokenEndpointUrl;
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    public TokenStore redisTokenStore (){
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    /**
     * redisTokenStore 采用远程访问的方式
     * @author zfj
     * @date 2022/2/17
     */
//    @Bean
//    public RemoteTokenServices tokenService() {
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setClientId("app-one");
//        tokenService.setClientSecret(new BCryptPasswordEncoder().encode("123"));
//        tokenService.setCheckTokenEndpointUrl("http://127.0.0.1:9900/oauther2/oauth/check_token");
//        return tokenService;
//    }

    @Bean("myTokeStore")
    public TokenStore jwtTokenStore() {
        //采用jwt保存，实际上并没有保存，jwt是去中心化加密，不需要保存的，校验通过即可
        JwtTokenStore jwtTokenStore = new JwtTokenStore(getJwtAccessTokenConverter());
        return jwtTokenStore;
    }
    /**
     * 设置加盐
     * @author zfj
     * @date 2022/1/13
     */
    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("sign");
        //用于验证加盐秘钥
        jwtAccessTokenConverter.setVerifierKey("sign");
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenServices(tokenService()).resourceId("app1");

        //资源服务配置jwtTokenStore
        resources.tokenStore(jwtTokenStore()).resourceId("app2");
    }
}
