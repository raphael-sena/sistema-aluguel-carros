package com.lab.backend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Usuario {
    private String endereco;
    // private List<PedidoAluguel> pedidos;
    private String entidadeEmpregadora;
    // private List<Rendimento> rendimentos;
}
