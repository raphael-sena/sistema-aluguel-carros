package com.lab.backend.models;

import com.lab.backend.models.enums.TipoCliente;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.ORDINAL)
    private TipoCliente tipoCliente;

    public Cliente(Long id, String nome, String email, String senha, String endereco, String entidadeEmpregadora, TipoCliente tipoCliente) {
        super(id, nome, email, senha);
        this.endereco = endereco;
        this.entidadeEmpregadora = entidadeEmpregadora;
        this.tipoCliente = tipoCliente;
    }
}
