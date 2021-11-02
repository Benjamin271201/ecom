package com.nashtech.ecommerce.auth;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.security.ApplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationUser implements UserDetails {
    private final int id;
    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final String username;
    private final String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;


    public static ApplicationUser build(Account account) {
        ApplicationUserRole accRole = account.isAdmin() ? ApplicationUserRole.ADMIN : ApplicationUserRole.CUSTOMER;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(accRole.name()));

        return new ApplicationUser(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.isBanned(),
                authorities
        );
    }

    public ApplicationUser(int id, String username, String password, boolean isBanned,
                           List<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = !isBanned;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
