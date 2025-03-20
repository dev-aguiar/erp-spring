package com.devaguiar.erp.repositories;

import com.devaguiar.erp.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
