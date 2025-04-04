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
    private String entidadeEmpregadora;
    // private List<Rendimento> rendimentos;

    @OneToMany(mappedBy = "cliente")
    List<PedidoAluguel> pedidos;

    @OneToMany(mappedBy = "dono")
    private List<Veiculo> veiculos = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private TipoCliente tipoCliente;
}
