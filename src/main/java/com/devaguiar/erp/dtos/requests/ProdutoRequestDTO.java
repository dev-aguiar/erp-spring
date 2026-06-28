package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank
        String nome,

        @NotNull
        @Positive
        @Digits(integer = 8, fraction = 2)
        BigDecimal preco,

        @NotNull
        @PositiveOrZero
        Integer quantidade) {
}
