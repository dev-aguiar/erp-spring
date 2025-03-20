package com.devaguiar.erp.entities;

import com.devaguiar.erp.dtos.requests.ProdutoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens;

    public Produto(ProdutoRequestDTO data) {
        this.nome = data.nome();
        this.preco = data.preco();
        this.quantidade = data.quantidade();
    }
}
