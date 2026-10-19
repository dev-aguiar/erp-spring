package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.*;

public record ItemPedidoRequestDTO(
        @NotNull
        Long pedidoId,

        @NotNull
        Long produtoId,

        @NotNull
        @Positive
        Integer quantidade) {
}
