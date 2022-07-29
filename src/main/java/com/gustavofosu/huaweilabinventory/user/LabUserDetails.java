package com.gustavofosu.huaweilabinventory.user;

import com.gustavofosu.huaweilabinventory.role.HLabRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class LabUserDetails implements UserDetails {

    private HLabUsers hLabUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<HLabRole> roles = hLabUser.getUserRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (HLabRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return hLabUser.getPassword();
    }

    @Override
    public String getUsername() {
        return hLabUser.getUsername();
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

    public boolean hasRole(String roleName) {
        return this.hLabUser.hasRole(roleName);
    }
}
