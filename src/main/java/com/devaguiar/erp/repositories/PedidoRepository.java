package com.devaguiar.erp.repositories;

import com.devaguiar.erp.entities.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @EntityGraph(attributePaths = {"cliente", "vendedor"})
    List<Pedido> findAll();
}
