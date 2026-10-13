package com.devaguiar.erp.entities;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import com.devaguiar.erp.exceptions.EstoqueInsuficienteException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens;

    public Produto(ProdutoRequestDTO data) {
        this.nome = data.nome();
        this.preco = data.preco();
        this.quantidade = data.quantidade();
    }
    
    public void darBaixaNoEstoque(int quantidadeSolicitada) {
        int disponivel = this.quantidade == null ? 0 : this.quantidade;
        if (disponivel < quantidadeSolicitada) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para o produto '" + this.nome + "'. Disponível: "
                            + disponivel + ", solicitado: " + quantidadeSolicitada);
        }
        this.quantidade = disponivel - quantidadeSolicitada;
    }
}
