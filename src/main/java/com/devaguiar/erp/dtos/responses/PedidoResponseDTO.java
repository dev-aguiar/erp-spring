package com.devaguiar.erp.dtos.responses;

import com.devaguiar.erp.entities.Pedido;
import com.devaguiar.erp.entities.Vendedor;
import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoResponseDTO(Long id, List<ClienteResumidoDTO> cliente, Vendedor vendedor, LocalDate dataPedido,
                                FormaPagamento formaPagamento, StatusPedido statusPedido) {

    public PedidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getClienteResumido(), pedido.getVendedor(), pedido.getDataPedido(), pedido.getFormaPagamento(), pedido.getStatusPedido());
    }
}
