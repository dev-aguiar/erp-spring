package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.enums.StatusPedido;

import java.time.LocalDate;

public record PedidoResumidoResponseDTO(Long id, LocalDate dataPedido, StatusPedido statusPedido) {

    public PedidoResumidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getDataPedido(), pedido.getStatusPedido());
    }
}
