package com.lab.backend.models;

import com.lab.backend.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_aluguel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    @Enumerated
    private Status status;

    @OneToOne(mappedBy = "pedidoAluguel")
    @JoinColumn(name = "avaliacao_aluguel_id")
    private AvaliacaoAluguel avaliacaoAluguel;
}
