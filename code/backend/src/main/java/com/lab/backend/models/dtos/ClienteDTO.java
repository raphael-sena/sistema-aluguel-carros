package com.lab.backend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String entidadeEmpregadora;
}
