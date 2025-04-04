package com.lab.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avaliacao_aluguel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AvaliacaoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean aprovado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Agente agente;

    @OneToOne
    @JoinColumn(name = "pedido_aluguel_id")
    private PedidoAluguel pedidoAluguel;


}
