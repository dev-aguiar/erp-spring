package com.devaguiar.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devaguiar.erp.entities.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
