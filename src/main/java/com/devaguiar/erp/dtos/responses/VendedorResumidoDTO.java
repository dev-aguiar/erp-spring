package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Vendedor;

public record VendedorResumidoDTO(Long id, String nome) {

    public VendedorResumidoDTO(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome());
    }
}
