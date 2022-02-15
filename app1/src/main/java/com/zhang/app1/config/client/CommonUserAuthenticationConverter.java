package com.zhang.app1.config.client;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class CommonUserAuthenticationConverter implements UserAuthenticationConverter {
    private static final Logger log = LoggerFactory.getLogger(CommonUserAuthenticationConverter.class);
    private static final String N_A = "N/A";
    private static final String CLIENT_ID = "client_id";

    public CommonUserAuthenticationConverter() {
    }

    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap();
        response.put("user_name", authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }

        return response;
    }

    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey("user_name")) {
            Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);
            String username = (String)map.get("user_name");
            String clientId = (String)map.get("client_id");
            log.info("用户账号:{},clientId:{}", username, clientId);
            User user = (User)JSON.parseObject((String)map.get("loginUser"), SysUserVO.class);
            return new UsernamePasswordAuthenticationToken(user, "N/A", authorities);
        } else {
            return null;
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get("authorities");
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
        } else if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection)authorities));
        } else {
            throw new IllegalArgumentException("Authorities must be either a String or a Collection");
        }
    }
}
