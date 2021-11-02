package com.nashtech.ecommerce.auth;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository
                .findAccountByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("Username %s not found", username)));
        return ApplicationUser.build(account);
    }
}
