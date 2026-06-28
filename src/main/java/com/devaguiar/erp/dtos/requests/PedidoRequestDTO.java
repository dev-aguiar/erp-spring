package com.devaguiar.erp.dtos.requests;

import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PedidoRequestDTO(
        @NotNull
        Long clienteId,

        @NotNull
        Long vendedorId,

        @NotNull
        LocalDate dataPedido,

        @NotNull
        StatusPedido statusPedido,

        @NotNull
        FormaPagamento formaPagamento) {
}
