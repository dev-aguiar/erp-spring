package com.devaguiar.erp.repositories;

import com.devaguiar.erp.entities.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    @EntityGraph(attributePaths = "pedidos")
    List<Cliente> findAll();

    @Override
    @EntityGraph(attributePaths = "pedidos")
    Optional<Cliente> findById(Long id);
}
