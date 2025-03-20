package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.ItemPedido;

public record ItemPedidoResponseDTO(Long id, Long produtoId, Integer quantidade, Double valorUnitario) {

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this(itemPedido.getId(), itemPedido.getProduto().getId(), itemPedido.getQuantidade(), itemPedido.getValorUnitario());
    }
}
