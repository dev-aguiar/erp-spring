package com.devaguiar.erp.entities;

import com.devaguiar.erp.dtos.requests.ClienteRequestDTO;
import com.devaguiar.erp.dtos.responses.PedidoResumidoResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente(ClienteRequestDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.endereco = data.endereco();
    }
}
