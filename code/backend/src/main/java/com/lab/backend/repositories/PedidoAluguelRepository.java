package com.lab.backend.repositories;

import com.lab.backend.models.PedidoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
}
