package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Cliente;

public record ClienteResumidoDTO(Long id , String nome) {

    public ClienteResumidoDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome());
    }
}
