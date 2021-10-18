package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    //Constructor
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account customerAccount) {
        customerAccount.setAdmin(false);
        customerAccount.setBanned(false);
        return accountRepository.save(customerAccount);
    }

    public Account addAdminAccount(Account adminAccount) {
        adminAccount.setAdmin(true);
        adminAccount.setBanned(true);
        return accountRepository.save(adminAccount);
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

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }
}
