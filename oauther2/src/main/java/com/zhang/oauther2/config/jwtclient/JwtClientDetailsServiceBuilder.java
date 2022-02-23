package com.zhang.oauther2.config.jwtclient;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class JwtClientDetailsServiceBuilder extends ClientDetailsServiceBuilder<JwtClientDetailsServiceBuilder> {

    private Map<String, ClientDetails> clientDetails = new ConcurrentHashMap();
    private PasswordEncoder passwordEncoder;

    public JwtClientDetailsServiceBuilder() {
    }

    public JwtClientDetailsServiceBuilder passwordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    @Override
    protected void addClient(String clientId, ClientDetails build) {
        this.clientDetails.put(clientId, build);
    }

    @Override
    protected ClientDetailsService performBuild() {
        JwtClientDetailsService redisClientDetailsService = new JwtClientDetailsService();
        if (this.passwordEncoder != null) {
            redisClientDetailsService.setPasswordEncoder(this.passwordEncoder);
        }

        redisClientDetailsService.setClientDetailsStore(this.clientDetails);
        return redisClientDetailsService;
    }
}
