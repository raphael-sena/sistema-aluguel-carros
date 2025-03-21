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

    public Cliente(Long id, String nome, String email, String senha, String endereco, String entidadeEmpregadora) {
        super(id, nome, email, senha);
        this.endereco = endereco;
        this.entidadeEmpregadora = entidadeEmpregadora;
    }
}
