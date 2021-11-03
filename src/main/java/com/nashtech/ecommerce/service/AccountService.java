package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.AccountDTO;
import com.nashtech.ecommerce.exception.*;
import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.repository.AccountRepository;
import com.nashtech.ecommerce.security.SecurityUtils;
import org.springframework.stereotype.Service;


import javax.security.auth.login.FailedLoginException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class AccountService {
    //Errors
    private static final String ACC_NOT_FOUND = "Account not found!";

    //Constructor
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO findAccountById(int id) {
        return new AccountDTO(accountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND)));
    }

    public AccountDTO findAccountByUsername(String username) {
        return new AccountDTO(accountRepository
                .findAccountByUsername(username)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND)));
    }

    public List<AccountDTO> getAllAccount() {
        return accountRepository.findAll().stream().map(AccountDTO::new).toList();
    }

    public void updatePassword(int id, String newPassword) {
        accountRepository.updatePassword(id, newPassword);
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
