package com.devaguiar.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devaguiar.erp.entities.Vendedor;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
