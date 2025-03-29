package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDTO {

    @JsonProperty("fromAccount")
    @NotNull(message = "La cuenta origen es obligatoria")
    private Integer fromAccount;

    @JsonProperty("toAccount")
    @NotNull(message = "La cuenta destino es obligatoria")
    private Integer toAccount;

    @JsonProperty("monto")
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private BigDecimal amount;

}

