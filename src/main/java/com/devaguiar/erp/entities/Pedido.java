package com.devaguiar.erp.entities;

import com.devaguiar.erp.dtos.requests.PedidoRequestDTO;
import com.devaguiar.erp.dtos.responses.ClienteResumidoDTO;
import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;
import com.devaguiar.erp.repositories.ClienteRepository;
import com.devaguiar.erp.repositories.VendedorRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    private LocalDate dataPedido;
    private FormaPagamento formaPagamento;
    private StatusPedido statusPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public Pedido(PedidoRequestDTO data, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        this.cliente = clienteRepository.findById(data.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        this.vendedor = vendedorRepository.findById(data.vendedorId())
                .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado"));
        this.dataPedido = data.dataPedido();
        this.statusPedido = data.statusPedido();
    }

    public List<ClienteResumidoDTO> getClienteResumido() {
        return List.of(new ClienteResumidoDTO(cliente.getId(), cliente.getNome()));
    }
}
