package com.devaguiar.erp.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdicionarProdutoPedidoRequestDTO(

        @NotNull
        Long pedidoId,

        @NotNull
        Long produtoId,

        @Positive
        int quantidade) {
}
