//package com.zhang.app1.config.client;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//
///**
// * @Copyright 深圳金雅福控股集团有限公司
// * @Author: zhangfanjun
// * @Date 2021/11/17
// * @Version: 1.0
// */
//public class SysUserVO extends User {
//    private static final long serialVersionUID = -642641310958554579L;
//    private String platform = "system";
//    private Long userId;
//    private String fullName;
//
//    public SysUserVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password == null ? "" : password, authorities);
//    }
//
//    public SysUserVO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long userId, String fullName) {
//        super(username, password == null ? "" : password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.fullName = fullName;
//        this.userId = userId;
//    }
//    public Long getUserId() {
//        return this.userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getFullName() {
//        return this.fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//}