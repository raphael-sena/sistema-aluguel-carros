package com.lab.backend.models;

import com.lab.backend.models.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private TipoCliente tipoCliente;

    public Cliente(Long id, String nome, String email, String senha, String endereco, String entidadeEmpregadora, TipoCliente tipoCliente) {
        super(id, nome, email, senha);
        this.endereco = endereco;
        this.entidadeEmpregadora = entidadeEmpregadora;
        this.tipoCliente = tipoCliente;
    }
}
