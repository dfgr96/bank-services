package com.bank.account_service.controller;

import com.bank.account_service.model.Account;
import com.bank.account_service.service.AccountService;
import com.example.AccountRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequestDTO accountRequest) {
        Account newAccount = accountService.createAccount(accountRequest.getName(), accountRequest.getInitialBalance());
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}
