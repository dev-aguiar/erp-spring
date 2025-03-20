package com.devaguiar.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devaguiar.erp.entities.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
