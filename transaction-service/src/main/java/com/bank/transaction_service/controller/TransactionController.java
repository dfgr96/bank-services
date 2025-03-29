package com.bank.transaction_service.controller;

import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.service.TransactionService;
import com.example.TransactionRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> transferMoney(@Valid @RequestBody TransactionRequestDTO transactionRequest) {
        Transaction transaction = transactionService.transferMoney(
                transactionRequest.getFromAccount(),
                transactionRequest.getToAccount(),
                transactionRequest.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Integer accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

}
