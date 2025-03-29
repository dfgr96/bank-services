package com.example;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {

    private Integer id;
    private String nombre;
    private BigDecimal saldo;

}
