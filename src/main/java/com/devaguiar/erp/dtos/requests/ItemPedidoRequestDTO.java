package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
        @NotNull
        Long pedidoId,

        @NotNull
        Long produtoId,

        @NotNull
        @PositiveOrZero
        Integer quantidade,

        @NotNull
        @Positive
        @Digits(integer = 8, fraction = 2)
        BigDecimal valorUnitario) {
}
