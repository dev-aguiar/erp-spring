package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id, String nome, BigDecimal preco, Integer quantidade) {

    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }
}
