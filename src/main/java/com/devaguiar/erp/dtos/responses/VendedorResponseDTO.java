package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Vendedor;

import java.time.LocalDate;

public record VendedorResponseDTO(Long id, String nome, LocalDate dataNascimento) {

    public VendedorResponseDTO(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getDataNascimento());
    }
}
