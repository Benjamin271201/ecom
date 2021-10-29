package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.exception.*;
import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.repository.AccountRepository;
import org.springframework.stereotype.Service;


import javax.security.auth.login.FailedLoginException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class AccountService {
    //Errors
    private static final String ACC_NOT_FOUND = "Account not found!";
//    private static final String USERNAME_NOT_FOUND = "Username not found!";
    private static final String USERNAME_ALREADY_EXISTS = "Username already exists!";


    //Constructor
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccountById(int id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND));
    }

    public Account findAccountByUsername(String username) {
        return accountRepository
                .findAccountByUsername(username)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND));
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account addCustomerAccount(Account customerAccount) {
        if (existsAccountByUsername(customerAccount.getUsername())) {
            throw new AlreadyExistsException(USERNAME_ALREADY_EXISTS);
        }
        customerAccount.setJoinDate();
        customerAccount.setAdmin(false);
        customerAccount.setBanned(false);
        return accountRepository.save(customerAccount);
    }

    public Account addAdminAccount(Account adminAccount) {
        if (existsAccountByUsername(adminAccount.getUsername())) {
            throw new AlreadyExistsException(USERNAME_ALREADY_EXISTS);
        }
        adminAccount.setJoinDate(Date.valueOf(LocalDate.now()));
        adminAccount.setAdmin(true);
        adminAccount.setBanned(false);
        return accountRepository.save(adminAccount);
    }

    public Account login(String username, String password) throws FailedLoginException {
        Account account = findAccountByUsername(username);
        if (!account.getPassword().equals(password)) throw new FailedLoginException();
        return account;
    }

    //SS
    //needs to check if the requested user has the right to modify account
    public Account updateUsername(Account account, String newUsername) {
        if (!existsAccountById(account.getId())) {
            throw new NotFoundException(ACC_NOT_FOUND);
        }
        //check if new username != old username
        if (!account.getUsername().equals(newUsername)) {
            //check if username exists
            if (existsAccountByUsername(newUsername)) {
                throw new AlreadyExistsException(USERNAME_ALREADY_EXISTS);
            }
            account.setUsername(newUsername);
        }
        return accountRepository.save(account);
    }

    //SS
    //needs to check if the requested user has the right to modify account
    public Account updatePassword(Account account, String newPassword) {
        Account acc = findAccountById(account.getId());
        acc.setPassword(newPassword);
        return accountRepository.save(acc);
    }

    public void deactivateAccount(int id) {
        if (!existsAccountById(id)) {
            throw new NotFoundException(ACC_NOT_FOUND);
        }
        accountRepository.deactivateAccount(id);
    }

    public boolean existsAccountByUsername(String username) {
        return accountRepository.existsAccountByUsername(username);
    }

    public boolean existsAccountById(int id) {
        return accountRepository.existsById(id);
    }
}
