package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping ("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        if (accountService.existsAccountByUsername(account.getUsername())) {
            throw new BadRequestException("Username already existed!");
        }
        return ResponseEntity.ok(accountService.addAccount(account));
    }

}
