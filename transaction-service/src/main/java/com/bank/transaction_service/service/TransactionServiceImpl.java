package com.bank.transaction_service.service;

import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction transferMoney(Integer fromAccount, Integer toAccount, BigDecimal amount) {
        // Lógica para transferir dinero entre cuentas (lo haremos después)
        // Registrar la transacción en la BD
        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Integer accountId) {
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }

}
