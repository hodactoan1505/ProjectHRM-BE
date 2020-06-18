package com.brycen.hrm.model.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.brycen.hrm.model.UserEntity;

/**
 * [Description]:Class convert userEntity to UserDetail of security use [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class CurrentUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserEntity userEntity;

    public CurrentUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().getName()));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
