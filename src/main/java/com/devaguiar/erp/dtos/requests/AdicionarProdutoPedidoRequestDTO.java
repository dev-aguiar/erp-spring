package com.devaguiar.erp.dtos.requests;

public record AdicionarProdutoPedidoRequestDTO(Long pedidoId, Long produtoId, int quantidade) {
}
