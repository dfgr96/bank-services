package com.bank.account_service.service;

import com.bank.account_service.model.Account;
import com.bank.account_service.repository.AccountRepository;
import com.example.UpdateBalanceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(String name, BigDecimal initialBalance) {
        Account account = new Account(name, initialBalance);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
    }

    @Override
    @Transactional
    public void updateBalance(UpdateBalanceDTO updateBalance) {
        Account account = accountRepository.findById(updateBalance.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal newBalance = account.getSaldo().add(updateBalance.getAmount());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        account.setSaldo(newBalance);
        accountRepository.save(account);
    }

}
