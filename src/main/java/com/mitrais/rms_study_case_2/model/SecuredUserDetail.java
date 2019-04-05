package com.mitrais.rms_study_case_2.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SecuredUserDetail implements UserDetails {

    private User user;

    private Set<Authority> authorities = new HashSet<>();

    public SecuredUserDetail(User user, Set<Authority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        Set<Authority> authorities = user.getAuthorities();

        return this.authorities
                .stream()
                .map(authority ->
                        new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    public String getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUserDetails() {
        return user;
    }

}
