package com.bank.transaction_service.repository;

import com.bank.transaction_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByFromAccountOrToAccount(Integer fromAccount, Integer toAccount);
}
