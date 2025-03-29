package com.bank.transaction_service.service;

import com.example.TransactionDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventPublisherImpl implements TransactionEventPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public TransactionEventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishTransactionEvent(TransactionDTO transaction) {
        rabbitTemplate.convertAndSend(exchange, routingKey, transaction);
        System.out.println("Evento de transacción enviado: " + transaction);
    }

}
