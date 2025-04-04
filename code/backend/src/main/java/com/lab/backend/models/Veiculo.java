package com.lab.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ano;
    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente dono;

    @OneToOne(mappedBy = "veiculo")
    @JoinColumn(name = "pedido_aluguel_id", nullable = false)
    private PedidoAluguel pedidoAluguel;
}
