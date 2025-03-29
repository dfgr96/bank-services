package com.bank.transaction_service.service;

import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;
import com.example.AccountDTO;
import com.example.TransactionDTO;
import com.example.UpdateBalanceDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionEventPublisher eventPublisher;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, TransactionEventPublisher eventPublisher) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Transaction transferMoney(Integer fromAccount, Integer toAccount, BigDecimal amount) {

        AccountDTO fromAcc = accountService.getAccount(fromAccount);
        AccountDTO toAcc = accountService.getAccount(toAccount);

        validateAmount(fromAcc, amount);

        BigDecimal newBalanceFrom = fromAcc.getSaldo().subtract(amount);
        BigDecimal newBalanceTo = toAcc.getSaldo().add(amount);

        updateAccountBalance(fromAcc.getId(), newBalanceFrom);
        updateAccountBalance(toAcc.getId(), newBalanceTo);

        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionDTO transactionDTO = new TransactionDTO(
                savedTransaction.getFromAccount(),
                savedTransaction.getToAccount(),
                savedTransaction.getMonto()
        );
        eventPublisher.publishTransactionEvent(transactionDTO);

        return savedTransaction;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Integer accountId) {
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }

    private void validateAmount(AccountDTO fromAcc, BigDecimal amount) {
        if (fromAcc.getSaldo().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    private void updateAccountBalance(Integer accountId, BigDecimal newBalance) {
        UpdateBalanceDTO updateBalance = new UpdateBalanceDTO();
        updateBalance.setAccountId(accountId);
        updateBalance.setAmount(newBalance);

        accountService.updateAccountBalance(updateBalance);
    }

}
