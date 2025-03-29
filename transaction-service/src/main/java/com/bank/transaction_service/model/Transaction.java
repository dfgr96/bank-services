package com.bank.transaction_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_account", nullable = false)
    private Integer fromAccount;

    @Column(name = "to_account", nullable = false)
    private Integer toAccount;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    public Transaction(Integer fromAccount, Integer toAccount, BigDecimal monto) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }


}
