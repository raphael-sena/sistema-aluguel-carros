package com.lab.backend.models.dtos;

import com.lab.backend.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String entidadeEmpregadora;
    private TipoCliente tipoCliente;
}
