package com.lab.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluguel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AvaliacaoAluguel avaliacaoAluguel;

    // private List<Contrato> contratos = new ArrayList<>();

    private Double taxa;
}
