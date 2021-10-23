package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/account/find", params = {"id"})
    public Account getAccountById(@RequestParam("id") int id) {
        return accountService.findAccountById(id);
    }

    @GetMapping(value = "/account/find", params = {"u"})
    public Account getAccountByUsername(@RequestParam("u") String username) {
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/account/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccount();
    }

    @PostMapping("/account/add/customer")
    public ResponseEntity<Account> addCustomerAccount(@RequestBody Account account) throws Exception {
        Account result = accountService.addCustomerAccount(account);
        return ResponseEntity.created(new URI("/api/find/id" +result.getId())).body(result);
    }

    @PostMapping("/account/add/admin")
    public ResponseEntity<Account> addAdminAccount(@RequestBody Account account) throws Exception {
        Account result = accountService.addAdminAccount(account);
        return ResponseEntity.created(new URI("/api/find/id" + result.getId())).body(result);
    }

    @PutMapping("/account/update/username")
    public Account updateUsername(@RequestBody Account account) {
        return accountService.updateUsername(account, account.getUsername());
    }

    @PutMapping("/account/update/password")
    public Account updatePassword(@RequestBody Account account) {
        return accountService.updatePassword(account, account.getPassword());
    }

    @PutMapping("/account/disable")
    public void deactivateAccount(@RequestParam int id) {
        accountService.deactivateAccount(id);
    }

    @PostMapping("/account/login")
    public Account login(@RequestParam String username, @RequestParam String password) throws FailedLoginException {
        return accountService.login(username, password);
    }
}
