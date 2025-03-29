package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @JsonProperty("saldoInicial")
    @NotNull(message = "El saldo inicial es obligatorio")
    @Positive(message = "El saldo inicial debe ser mayor a 0")
    private BigDecimal initialBalance;
}