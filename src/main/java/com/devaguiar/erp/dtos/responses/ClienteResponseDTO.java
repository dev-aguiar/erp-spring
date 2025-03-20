package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Cliente;

import java.util.List;

public record ClienteResponseDTO(Long id, String nome, String email, String telefone, List<PedidoResumidoResponseDTO> pedidos) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), List.of());

    }
}

