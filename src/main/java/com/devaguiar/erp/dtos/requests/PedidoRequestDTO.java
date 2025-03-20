package com.devaguiar.erp.dtos.requests;

import com.devaguiar.erp.enums.StatusPedido;

import java.time.LocalDate;

public record PedidoRequestDTO(Long clienteId, Long vendedorId, LocalDate dataPedido, StatusPedido statusPedido) {
}
