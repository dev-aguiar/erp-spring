package com.devaguiar.erp.dtos.requests;

public record ItemPedidoRequestDTO(
        Long pedidoId,
        Long produtoId,
        Integer quantidade,
        Double valorUnitario) {
}
