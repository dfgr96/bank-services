package com.bank.transaction_service.service;

import com.example.TransactionDTO;

public interface TransactionEventPublisher {

    void publishTransactionEvent(TransactionDTO transaction);
}
