package com.bank.account_service.service;

import com.bank.account_service.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {

    Account createAccount(String nombre, BigDecimal saldoInicial);
    Account getAccountById(Integer id);
}
