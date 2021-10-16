package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account customer) {
        return accountRepository.save(customer);
    }

    public Account updateAccount(Account customer) {
        return accountRepository.save(customer);
    }
    public void deactivateAccount(int id) {
        accountRepository.deactiveAccount(id);
    }

    public boolean existsAccountByUsername(String username) {
        return accountRepository.existsAccountByUsername(username);
    }
}
