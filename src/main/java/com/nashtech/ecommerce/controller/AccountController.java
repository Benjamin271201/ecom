package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/account-management")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
//
//    @GetMapping(value = "/accounts/id")
//    public Account getAccountById(@RequestParam int id) {
//        return accountService.findAccountById(id);
//    }

    @GetMapping(value = "/accounts/")
    public Account getAccountByUsername(@RequestParam String username) {
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/accounts/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccount();
    }

    @PostMapping("/accounts/customers")
    public ResponseEntity<Account> addCustomerAccount(@RequestBody Account account) throws Exception {
        Account result = accountService.addCustomerAccount(account);
        return ResponseEntity.created(new URI("/api/find/id" +result.getId())).body(result);
    }

    @PostMapping("/accounts/admins")
    public ResponseEntity<Account> addAdminAccount(@RequestBody Account account) throws Exception {
        Account result = accountService.addAdminAccount(account);
        return ResponseEntity.created(new URI("/api/find/id" + result.getId())).body(result);
    }

    @PostMapping("/login")
    public Account login(@RequestParam String username, @RequestParam String password) throws FailedLoginException {
        return accountService.login(username, password);
    }

    @PutMapping("/accounts/username")
    public Account updateUsername(@RequestBody Account account) {
        return accountService.updateUsername(account, account.getUsername());
    }

    @PutMapping("/accounts/password")
    public Account updatePassword(@RequestBody Account account) {
        return accountService.updatePassword(account, account.getPassword());
    }

    @DeleteMapping("/accounts/id")
    public void deactivateAccount(@RequestParam int id) {
        accountService.deactivateAccount(id);
    }

}
