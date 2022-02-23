package com.zhang.oauther2.config.myclientdetail;

import com.alibaba.fastjson.JSON;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class MyClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private static final Map<String, String> temp = MyClientDetailsInfo.temp;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 配置密码的同时返回当前对象
     * @author zfj
     * @date 2022/2/23
     */
    public MyClientDetailsService passwordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }
    /**
     * 根据客户id加载客户信息
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        String v = temp.get(clientId);
        if (Objects.isNull(v)) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        BaseClientDetails baseClientDetails = JSON.parseObject(v, BaseClientDetails.class);
        return baseClientDetails;
    }

    /**
     * 添加新的客户
     */
    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        try {
            temp.put(clientDetails.getClientId(), JSON.toJSONString(clientDetails));
        } catch (Exception e) {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), e);
        }
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        String v = temp.get(clientDetails.getClientId());
        if (Objects.isNull(v)) {
            throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }
        temp.put(clientDetails.getClientId(), JSON.toJSONString(clientDetails));
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        String v = temp.get(clientId);
        if (Objects.isNull(v)) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
        BaseClientDetails baseClientDetails = JSON.parseObject(v, BaseClientDetails.class);
        baseClientDetails.setClientSecret(passwordEncoder.encode(secret));
        temp.put(clientId, JSON.toJSONString(baseClientDetails));
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        String v = temp.remove(clientId);
        if (Objects.isNull(v)) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        Collection<String> values = temp.values();
        List<BaseClientDetails> collect = values.stream().map(x -> JSON.parseObject(x, BaseClientDetails.class)).collect(Collectors.toList());
        List<ClientDetails> clientDetails = JSON.parseArray(JSON.toJSONString(collect), ClientDetails.class);
        return clientDetails;
    }
}
