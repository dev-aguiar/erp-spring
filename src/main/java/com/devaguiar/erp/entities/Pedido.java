package com.devaguiar.erp.entities;

import com.devaguiar.erp.enums.FormaPagamento;
import com.devaguiar.erp.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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

    public Pedido(Cliente cliente, Vendedor vendedor, LocalDate dataPedido, FormaPagamento formaPagamento, StatusPedido statusPedido) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.formaPagamento = formaPagamento;
    }
}
