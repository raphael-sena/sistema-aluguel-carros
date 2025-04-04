package com.lab.backend.repositories;

import com.lab.backend.models.AvaliacaoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoAluguelRepository extends JpaRepository<AvaliacaoAluguel, Long> {
}
