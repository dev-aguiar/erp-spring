package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(Long id, Long produtoId, Integer quantidade, BigDecimal valorUnitario) {

    public ItemPedidoResponseDTO(ItemPedido itemPedido) {
        this(itemPedido.getId(), itemPedido.getProduto().getId(), itemPedido.getQuantidade(), itemPedido.getValorUnitario());
    }
}
