package com.bank.account_service.message;

import com.bank.account_service.service.AccountService;
import com.example.TransactionDTO;
import com.example.UpdateBalanceDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventListener {

    private final AccountService accountService;

    public TransactionEventListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleTransactionEvent(TransactionDTO transactionDTO) {
        System.out.println("Evento recibido: " + transactionDTO);

        UpdateBalanceDTO updateBalanceToAccount = new UpdateBalanceDTO(
                transactionDTO.getToAccount(),
                transactionDTO.getAmount()
        );

        UpdateBalanceDTO updateBalanceFromAccount = new UpdateBalanceDTO(
                transactionDTO.getFromAccount(),
                transactionDTO.getAmount().negate()
        );

        accountService.updateBalance(updateBalanceToAccount);
        accountService.updateBalance(updateBalanceFromAccount);

        System.out.println("✔️ Saldo actualizado correctamente.");
    }
}
