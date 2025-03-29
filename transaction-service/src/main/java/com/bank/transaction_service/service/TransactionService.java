package com.bank.transaction_service.service;

import com.bank.transaction_service.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction transferMoney(Integer fromAccount, Integer toAccount, BigDecimal amount);
    List<Transaction> getTransactionsByAccount(Integer accountId);
}
