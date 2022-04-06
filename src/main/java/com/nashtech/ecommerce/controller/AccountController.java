package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.dto.AccountDTO;
import com.nashtech.ecommerce.security.SecurityUtils;
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

    @GetMapping(value = "/accounts/id/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccountDTO getAccountById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }

    @GetMapping(value = "/accounts/username/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccountDTO getAccountByUsername(@PathVariable("username") String username){
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccount();
    }

    @PutMapping("/accounts")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public void updatePassword(@RequestParam int id, @RequestParam String newPassword) {
        SecurityUtils.isForbidden(id);
        accountService.updatePassword(id, newPassword);
    }

    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deactivateAccount(@PathVariable("id") int id) {
        accountService.deactivateAccount(id);
    }
}
