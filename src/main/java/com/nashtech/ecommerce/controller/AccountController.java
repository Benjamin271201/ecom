package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/account-management")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //TODO: consider changing this to admin only
    @GetMapping(value = "/accounts/id/{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }

    @GetMapping(value = "/accounts/username/{username}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Account getAccountByUsername(@PathVariable("username") String username){
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccount();
    }

    @PostMapping("/admin-register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> addAdminAccount(@RequestBody Account account) throws Exception {
        Account result = accountService.addAdminAccount(account);
        return ResponseEntity.created(new URI("/api/find/id" + result.getId())).body(result);
    }

    @PutMapping("/accounts/password")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public void updatePassword(@RequestParam int id, @RequestParam String newPassword) {
        accountService.updatePassword(id, newPassword);
    }

    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivateAccount(@PathVariable("id") int id) {
        accountService.deactivateAccount(id);
    }
}
